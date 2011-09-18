package modules.taskModule.actions;

// TODO: clean up that file

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Vector;

import modules.taskModule.TaskModule;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.fields.field.Field;
import newtonERP.viewers.viewerData.BaseViewerData;
import newtonERP.viewers.viewerData.ImgViewerData;

/**
 * visualisation de la structure de la DB
 * 
 * @author CloutierJo
 */
public class DbGraph extends AbstractAction {
	private Hashtable<String, VisualEntity> vEntity;
	private int maxX;
	private int maxY;

	/**
	 */
	public DbGraph() {
		super(null);
	}

	@Override
	public BaseViewerData doAction(AbstractEntity sourceFieldEntity, Hashtable<String, String> parameters) {
		maxX = 0;
		maxY = 0;
		vEntity = new Hashtable<String, VisualEntity>();
		visualGraph vgraph = new visualGraph();
		vgraph.designGraph();
		BufferedImage bi = new BufferedImage(maxX, maxY + 8, BufferedImage.TYPE_BYTE_BINARY);
		Graphics g = bi.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		g.setColor(Color.BLACK);
		for(VisualEntity entity : vEntity.values()){
			g.drawImage(entity.getDrawEntity(), entity.x, entity.y, null);
		}

		ImgViewerData ret = new ImgViewerData(bi, "testPic");
		ret.setTitle("shema de la DB");
		return ret;
	}

	protected Hashtable<String, VisualEntity> getvEntity() {
		return vEntity;
	}

	protected void setvEntity(Hashtable<String, VisualEntity> vEntity) {
		this.vEntity = vEntity;
	}

	protected int getMaxX() {
		return maxX;
	}

	protected void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	protected int getMaxY() {
		return maxY;
	}

	protected void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	private class visualGraph {
		Vector<String> tmpEntityNameList;
		int nextx;
		int presenty;

		public visualGraph() {
			nextx = 0;
			presenty = 0;
			for(AbstractOrmEntity entity : new TaskModule().getEntityDefinitionList().values()){
				getvEntity().put(entity.getSystemName(), new VisualEntity(entity));
			}

			tmpEntityNameList = new Vector<String>();
			for(String str : getvEntity().keySet()){
				tmpEntityNameList.add(str);
			}
		}

		public void designGraph() {
			for(VisualEntity entity : getvEntity().values()){
				if(tmpEntityNameList.remove(entity.getEntity().getSystemName())){
					drawGraph(entity);
				}
			}
		}

		public void drawGraph(VisualEntity entity) {
			if(nextx > 500){
				nextx = 0;
				presenty = getMaxY() + 10;
			}
			entity.x = nextx;
			nextx += entity.width + 15;
			if(getMaxX() < nextx){
				setMaxX(nextx);
			}

			entity.y = presenty;
			if(getMaxY() < entity.height + presenty){
				setMaxY(entity.height + presenty);
			}

			/*
			 * VisualEntity currentEntity; entity.entity.get("1=1"); System.out.println(entity.entity.getSystemName());
			 * for (String entityName : entity.entity.getSingleAccessorList() .keySet()) { currentEntity =
			 * vEntity.get(entityName); if (tmpEntityNameList.remove(currentEntity.entity .getSystemName())) { entity.x
			 * = nextx; nextx += entity.width + 15; if (maxX < nextx) maxX = nextx; entity.y = presenty; if (maxY <
			 * entity.height + presenty) maxY = entity.height + presenty; } }
			 */
		}
	}

	private class VisualEntity {
		private AbstractOrmEntity entity;

		private final static int lineHeight = 14;
		private final static int marge = 2;
		private final static int pad = 5;
		private final static int charWidth = 8;
		int inx = marge + pad;
		int x = marge;
		int iny = marge + lineHeight;
		int y = marge;
		int width = 1;
		int height = 1;

		public VisualEntity(AbstractOrmEntity entity) {
			this.entity = entity;
			width = entity.getSystemName().length() * charWidth;

			for(Field fld : entity.getFields()){
				if(fld.getSystemName().length() * charWidth > width){
					width = fld.getSystemName().length() * charWidth;
				}
			}
			height = (entity.getFields().getFields().size() + 1) * lineHeight + 2 * marge;
		}

		protected AbstractOrmEntity getEntity() {
			return entity;
		}

		protected void setEntity(AbstractOrmEntity entity) {
			this.entity = entity;
		}

		/**
		 * @return l'image généré
		 */
		public BufferedImage getDrawEntity() {
			BufferedImage bi = new BufferedImage(width + 2 * marge + pad, height + 2 * marge,
					BufferedImage.TYPE_BYTE_BINARY);
			Graphics g = bi.getGraphics();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
			g.setColor(Color.WHITE);

			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, lineHeight - 2));
			g.drawString(entity.getSystemName(), inx, iny);
			iny += lineHeight;
			g.setFont(new Font(Font.MONOSPACED, 0, lineHeight - 2));
			for(Field fld : entity.getFields()){
				g.drawString(fld.getSystemName(), inx, iny);
				iny += lineHeight;
			}
			g.drawRect(marge, marge, width + pad, height);
			g.drawLine(marge, lineHeight + marge, width + pad + 1, lineHeight + marge);
			return bi;
		}
	}
}
