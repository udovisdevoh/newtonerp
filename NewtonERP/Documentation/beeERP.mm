<map version="0.9.0">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1301879080698" ID="ID_1103643671" MODIFIED="1302551888366" TEXT="beeERP">
<node CREATED="1302553787302" ID="ID_577710673" MODIFIED="1308186434148" POSITION="right" TEXT="testing">
<node CREATED="1302553794522" ID="ID_751839155" MODIFIED="1302553818032" TEXT="unitTesting de tout les &#xe9;l&#xe9;ment interne de l&apos;application">
<node CREATED="1302553914915" ID="ID_1180593057" MODIFIED="1302554003697" TEXT="test&#xe9; obligatoirement">
<node CREATED="1302553926645" ID="ID_824476303" MODIFIED="1302553934736" TEXT="tout se qui est sous le data layer"/>
<node CREATED="1302553935352" ID="ID_1114478496" MODIFIED="1302553948244" TEXT="les entit&#xe9;s"/>
<node CREATED="1302553948894" ID="ID_1984200184" MODIFIED="1302553956006" TEXT="le task manager"/>
<node CREATED="1302553959345" ID="ID_934344086" MODIFIED="1302553971996" TEXT="le module de gestion de droit">
<node CREATED="1302553971997" ID="ID_536310433" MODIFIED="1302553983958" TEXT="testin tr&#xe8;s en profondeur... s&#xe9;curity risk"/>
</node>
<node CREATED="1302554004810" ID="ID_575466154" MODIFIED="1302554019283" TEXT="les interfaces de module et d&apos;extension">
<node CREATED="1302554019284" ID="ID_1578617029" MODIFIED="1302554050005" TEXT="test&#xe9; de mani&#xe8;re exaustive pour &#xe9;vit&#xe9; les probl&#xe8;me de r&#xe9;tro compatibilit&#xe9;"/>
</node>
</node>
<node CREATED="1302553889681" ID="ID_657509427" MODIFIED="1302553895556" TEXT="non test&#xe9;:">
<node CREATED="1302553879532" ID="ID_970262312" MODIFIED="1302553908121" TEXT="le serveur"/>
<node CREATED="1302553895557" ID="ID_1194803905" MODIFIED="1302553903886" TEXT="les vue html"/>
</node>
</node>
<node CREATED="1302553818552" ID="ID_1577925385" MODIFIED="1302553876302" TEXT="cr&#xe9;&#xe9; un serveur de test continue"/>
</node>
<node CREATED="1301879205962" ID="ID_1606969501" MODIFIED="1315061451880" POSITION="right" TEXT="donn&#xe9;e">
<node CREATED="1301879295228" ID="ID_1726697124" MODIFIED="1301879306264" TEXT="mutli type de donn&#xe9;e">
<node CREATED="1301879448458" ID="ID_1568065390" MODIFIED="1301879508387" TEXT="type">
<node CREATED="1301879306266" ID="ID_955414294" MODIFIED="1301879511009" TEXT="base de donn&#xe9;e (ORM)">
<node CREATED="1302551675266" ID="ID_1291105475" MODIFIED="1302551684438" TEXT="multi SGDB">
<node CREATED="1302551684439" ID="ID_547196181" MODIFIED="1302551687387" TEXT="sqlite"/>
<node CREATED="1302551687964" ID="ID_1348554309" MODIFIED="1302551691619" TEXT="postGreSQL"/>
<node CREATED="1302551692181" ID="ID_1514665024" MODIFIED="1302551694944" TEXT="MySQL"/>
</node>
<node CREATED="1302551734794" ID="ID_1268579052" MODIFIED="1302551838547" TEXT="selfTest">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      doit pouvoir v&#233;rifier sur demande que tout les acc&#232;s n&#233;sc&#233;ssaire sont pr&#233;sent sans modifier les donn&#233; pr&#233;sente
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1301879313868" ID="ID_10590509" MODIFIED="1301879514647" TEXT="fichier XML"/>
<node CREATED="1301879322827" ID="ID_406284454" MODIFIED="1301879516619" TEXT="page internet ((X)HTML)"/>
<node CREATED="1301879337097" ID="ID_1905790899" MODIFIED="1301879519455" TEXT="service SOAP">
<node CREATED="1302554427791" ID="ID_1549325243" MODIFIED="1302554432625" TEXT="serveur"/>
<node CREATED="1302554433114" ID="ID_713036546" MODIFIED="1302554436402" TEXT="client"/>
</node>
<node CREATED="1301879344737" ID="ID_704440370" MODIFIED="1301879521077" TEXT="service XML-RPC">
<node CREATED="1302554438367" ID="ID_868525455" MODIFIED="1302554440737" TEXT="client"/>
</node>
<node CREATED="1301879359735" ID="ID_1042028747" MODIFIED="1301879502869" TEXT="SVN et co"/>
<node CREATED="1301879353365" ID="ID_76024404" MODIFIED="1301879504951" TEXT="LDAP"/>
</node>
<node CREATED="1301879554290" ID="ID_358254953" MODIFIED="1301879570667" TEXT="type facilement ajoutable par:">
<node CREATED="1301879570669" ID="ID_757833185" MODIFIED="1301879575416" TEXT="extension"/>
<node CREATED="1301879575827" ID="ID_1709288243" MODIFIED="1301879600949" TEXT="sous-classement dans le code originale"/>
<node CREATED="1301879601393" ID="ID_1184204578" MODIFIED="1301879622907" TEXT="par fichier de configuration"/>
</node>
<node CREATED="1301879626661" ID="ID_645159723" MODIFIED="1301879655103" TEXT="configuration des source de donn&#xe9;es par fichier de config (XML)">
<node CREATED="1301879683902" ID="ID_604845761" MODIFIED="1301879689709" TEXT="selon le type"/>
<node CREATED="1301879690517" ID="ID_1542791154" MODIFIED="1302552246604" TEXT="le plus standard possible"/>
</node>
</node>
<node CREATED="1301879387740" ID="ID_1777959600" MODIFIED="1301879394736" TEXT="facilement accessible"/>
<node CREATED="1301879395357" ID="ID_1809732340" MODIFIED="1301879402339" TEXT="facilement modifiable">
<node CREATED="1302552260145" ID="ID_356965975" MODIFIED="1302552280569" TEXT="pour le type possible (ex:HTML impossible)"/>
</node>
<node CREATED="1301879407578" ID="ID_652651985" MODIFIED="1301879724011" TEXT="interface la plus simple possible mais complete..."/>
<node CREATED="1301880332944" ID="ID_61936753" MODIFIED="1301880377351" TEXT="revoir le fonctionnement des liaisons entre les donn&#xe9;es et explorer comment le faire avec les sources multiple"/>
<node CREATED="1301881028684" ID="ID_689984005" MODIFIED="1301881059202" TEXT="validation des donn&#xe9;e &#xe9;crite dans tout les cas et sanyty check sur les donn&#xe9;es externe qui sont utilis&#xe9;"/>
</node>
<node CREATED="1301879214628" ID="ID_1744739860" MODIFIED="1315061420308" POSITION="right" TEXT="module">
<node CREATED="1301879737612" ID="ID_1994862218" MODIFIED="1301879759811" TEXT="le modele de donn&#xe9; doit etre simple a cr&#xe9;&#xe9;">
<node CREATED="1301879759812" ID="ID_1097663904" MODIFIED="1315061463311" TEXT="pas de code ou code g&#xe9;n&#xe9;r&#xe9;"/>
<node CREATED="1301879764611" ID="ID_185942024" MODIFIED="1301879781382" TEXT="pas de fichier de config manuel"/>
<node CREATED="1301879782360" ID="ID_726906875" MODIFIED="1301879809962" TEXT="soft bulding (cr&#xe9;ation assist&#xe9;)"/>
<node CREATED="1301879811932" ID="ID_1084044118" MODIFIED="1301879824587" TEXT="doit &#xea;tre exportable/importable">
<node CREATED="1301879824588" ID="ID_1348731259" MODIFIED="1301879834912" TEXT="fichier XML"/>
<node CREATED="1301879835569" ID="ID_1822860261" MODIFIED="1301879844805" TEXT="avec ou sans donn&#xe9;es"/>
</node>
<node CREATED="1301879869972" ID="ID_917592996" MODIFIED="1301879902467" TEXT="un module peut avoir des enregistrement obligatoire a l&apos;installe">
<node CREATED="1301879902468" ID="ID_342102805" MODIFIED="1301879914127" TEXT="probablement avec le fichier d&apos;import"/>
</node>
</node>
<node CREATED="1301879917168" ID="ID_1355696019" MODIFIED="1301879955578" TEXT="les actions doivent tout permettre">
<node CREATED="1301879932019" ID="ID_1370993010" MODIFIED="1301879976430" TEXT="acc&#xe8;s directe au mod&#xe8;le de donn&#xe9; du module">
<node CREATED="1301879976431" ID="ID_542079368" MODIFIED="1301879984739" TEXT="pas n&#xe9;sc&#xe9;ssairement au autre"/>
</node>
</node>
<node CREATED="1301880236334" ID="ID_75427565" MODIFIED="1301880257497" TEXT="les vue possible sont d&#xe9;finie a l&apos;int&#xe9;rieur du module"/>
<node CREATED="1301881500435" ID="ID_539547830" MODIFIED="1315061389163" TEXT="les module interne sont fais en java(a l&apos;int&#xe9;rieur de BeeERP) les module externe sont fait sous forme d&apos;extension"/>
</node>
<node CREATED="1301879217094" ID="ID_617654158" MODIFIED="1308186384595" POSITION="right" TEXT="serveur">
<node CREATED="1301880474920" ID="ID_1630261416" MODIFIED="1301880497618" TEXT="ajout&#xe9; le support pour des vue ajax"/>
<node CREATED="1301880498180" ID="ID_1503936176" MODIFIED="1301880527459" TEXT="ajout&#xe9; la possibilit&#xe9; d&apos;offrir un service SAOP">
<node CREATED="1301880527460" ID="ID_1352288673" MODIFIED="1301880538290" TEXT="probablement pas par le meme serveur"/>
<node CREATED="1301880539722" ID="ID_752430869" MODIFIED="1301880593467" TEXT="acc&#xe8;s au donn&#xe9;e restraint selon les droit d&apos;acc&#xe8;s de l&apos;utilisateur qui a un acc&#xe8;s SOAP">
<node CREATED="1301880593468" ID="ID_23392980" MODIFIED="1301880649899" TEXT="ne peu &#xea;tre un utilisateur normale"/>
</node>
</node>
<node CREATED="1301880279302" ID="ID_1457279529" MODIFIED="1308186603394" TEXT="support&#xe9; TOMCAT"/>
</node>
<node CREATED="1301879225121" ID="ID_1344801408" MODIFIED="1308186388941" POSITION="right" TEXT="vue">
<node CREATED="1302555679963" ID="ID_932929924" MODIFIED="1302555691862" TEXT="refonte compl&#xe8;te du systeme de vue">
<node CREATED="1302555590873" ID="ID_1516030621" MODIFIED="1302555753417" TEXT="ensemble de widget standard permetant un d&#xe9;velopement rapide d&apos;un vue">
<node CREATED="1302555963351" ID="ID_1430415348" MODIFIED="1302556027893" TEXT="doivent tous avoir seulement des m&#xe9;thode static permetant de g&#xe9;n&#xe9;r&#xe9; le HTML n&#xe9;sc&#xe9;ssair epour l&apos;affichage du bouton comme voulue"/>
<node CREATED="1302555953237" ID="ID_1338557049" MODIFIED="1302557469620" TEXT="widget:">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      ne fais que sortir le HTML avec les param&#232;tre pris en compte, ne fais pas de traitement par rapport au donn&#233; (except&#233; les select, radio et checkbox ou on leur passe une liste d'option pouvant provenir d'une table)
    </p>
  </body>
</html></richcontent>
<node CREATED="1302555753418" ID="ID_358644251" MODIFIED="1302555961351" TEXT="formulaire">
<node CREATED="1302555800834" ID="ID_560629839" MODIFIED="1302555805021" TEXT="buttons"/>
<node CREATED="1302555806124" ID="ID_1006706885" MODIFIED="1302555808806" TEXT="checkbox">
<node CREATED="1302557486143" ID="ID_737923391" MODIFIED="1302557487380" TEXT="la liste d&apos;option peu provenir d&apos;une requette sur une entit&#xe9;"/>
</node>
<node CREATED="1302555810460" ID="ID_644388507" MODIFIED="1302555814471" TEXT="radio buttons">
<node CREATED="1302557481237" ID="ID_1933314703" MODIFIED="1302557484510" TEXT="la liste d&apos;option peu provenir d&apos;une requette sur une entit&#xe9;"/>
</node>
<node CREATED="1302555823161" ID="ID_1607388273" MODIFIED="1302555826513" TEXT="select">
<node CREATED="1302557347780" ID="ID_1745317916" MODIFIED="1302557366340" TEXT="la liste d&apos;option peu provenir d&apos;une requette sur une entit&#xe9;"/>
</node>
<node CREATED="1302555827302" ID="ID_1175339627" MODIFIED="1302555835122" TEXT="text input"/>
<node CREATED="1302555835678" ID="ID_1262887669" MODIFIED="1302555838615" TEXT="text area"/>
<node CREATED="1302555839113" ID="ID_971662588" MODIFIED="1302555845870" TEXT="file"/>
<node CREATED="1302555850874" ID="ID_1993209268" MODIFIED="1302555856126" TEXT="hidden"/>
</node>
</node>
<node CREATED="1302556117174" ID="ID_1612975543" MODIFIED="1302557274473" TEXT="megaWidget (trouv&#xe9; un autre nom)"/>
</node>
</node>
<node CREATED="1301880671231" ID="ID_1653137514" MODIFIED="1301880684390" TEXT="avec template html">
<node CREATED="1301880684391" ID="ID_568358295" MODIFIED="1301880710306" TEXT="explorer quel est le mailleur moyen de cr&#xe9;&#xe9; un moteur de template offrant tout les fonctionnalit&#xe9; n&#xe9;sc&#xe9;ssaire"/>
<node CREATED="1301880725468" ID="ID_303879325" MODIFIED="1301880733087" TEXT="doit rendre facile le ajax"/>
<node CREATED="1301883139282" ID="ID_820426184" MODIFIED="1301883147839" TEXT="gestion des formulaire">
<node CREATED="1301880737257" ID="ID_1090499393" MODIFIED="1301883148924" TEXT="validation de formulaire automatique cot&#xe9; client"/>
<node CREATED="1301883152011" ID="ID_1313458763" MODIFIED="1301883179908" TEXT="conception simplifier de formulaire">
<node CREATED="1301883179909" ID="ID_1075927033" MODIFIED="1301883267714" TEXT="widget simplement disponible">
<node CREATED="1301883218929" ID="ID_1015849531" MODIFIED="1301883230257" TEXT="listbox remplis a partir d&apos;une table"/>
<node CREATED="1301883231124" ID="ID_1330311391" MODIFIED="1301883260362" TEXT="list/text box autocomplete"/>
</node>
</node>
</node>
<node CREATED="1301880754201" ID="ID_1554644622" MODIFIED="1301880765812" TEXT="doit demeur&#xe9; fonctionnel sans JS"/>
<node CREATED="1301880769647" ID="ID_1213568964" MODIFIED="1301880830358" TEXT="fonctionnel dans IE9+, FF4+ chrome10+, safarie??+, opera??+ (toute les versions en date de 2011)"/>
<node CREATED="1301880839756" ID="ID_1401863166" MODIFIED="1301880846938" TEXT="utilisation du HTML5"/>
<node CREATED="1301880859007" ID="ID_261738924" MODIFIED="1301880865441" TEXT="jquery"/>
<node CREATED="1301880872223" ID="ID_514965314" MODIFIED="1301880891535" TEXT="gestion des header par le view manager (pas par les template)">
<node CREATED="1301880891537" ID="ID_389002283" MODIFIED="1301880914006" TEXT="rendre possible l&apos;ajout de css ou de javascript dans le header du template"/>
</node>
</node>
<node CREATED="1301880926829" ID="ID_1095790806" MODIFIED="1301880939506" TEXT="gestion des cache"/>
<node CREATED="1301880947524" ID="ID_1589091387" MODIFIED="1301881104082" TEXT="aucun code interne pour la cr&#xe9;ation des vue sp&#xe9;cifique aux modules">
<node CREATED="1301880970146" ID="ID_1971236373" MODIFIED="1301880977697" TEXT="tout est dans les template html">
<node CREATED="1301880981365" ID="ID_1888837420" MODIFIED="1301880992168" TEXT="s&apos;assurer de donn&#xe9; tout les possibilit&#xe9;"/>
</node>
</node>
</node>
<node CREATED="1301879987429" ID="ID_776232759" MODIFIED="1315061237141" POSITION="right" TEXT="gestion de droit">
<node CREATED="1301879991813" ID="ID_912333493" MODIFIED="1301880011221" TEXT="ajout&#xe9; un gestion des droits au niveau des module">
<node CREATED="1301880011223" ID="ID_1096100418" MODIFIED="1301880028519" TEXT="une action a le droit de touch&#xe9; a telle table/colone"/>
<node CREATED="1301880072757" ID="ID_778269647" MODIFIED="1301880109557" TEXT="probablement en ajoutant une sorte d&apos;utilisateur (systeme) pour chaque action"/>
</node>
<node CREATED="1301880036276" ID="ID_1763377052" MODIFIED="1301880050812" TEXT="rendre plus pr&#xe9;cise la gestion de droit">
<node CREATED="1301880050813" ID="ID_1078852897" MODIFIED="1301880069553" TEXT="jusqu&apos;a quelle colone peut &#xea;tre visible/modifiable par l&apos;utilisateur"/>
<node CREATED="1301882496235" ID="ID_1477941331" MODIFIED="1301882509569" TEXT="quel action peu &#xea;tre invoqu&#xe9; par un utilisateur"/>
<node CREATED="1301882510890" ID="ID_1233990143" MODIFIED="1301882525293" TEXT="quel tache peut &#xea;tre invoqu&#xe9; par un utilisateur"/>
<node CREATED="1301880126324" ID="ID_80005638" MODIFIED="1301880135702" TEXT="gestion des droit sur deux niveau">
<node CREATED="1301880135703" ID="ID_86508520" MODIFIED="1301880140837" TEXT="utilisateur"/>
<node CREATED="1301880141419" ID="ID_793759265" MODIFIED="1301880143296" TEXT="groupe"/>
<node CREATED="1301880144084" ID="ID_1866593609" MODIFIED="1301880155731" TEXT="un utilisateur peu &#xea;tre dans plusieur groupe"/>
<node CREATED="1301880156587" ID="ID_1933493047" MODIFIED="1301880207037" TEXT="on peu enlever un droit particulier a un utilisateur, mais pas a un groupe"/>
</node>
</node>
<node CREATED="1301881627826" ID="ID_1318478935" MODIFIED="1301882465232" TEXT="permettre l&apos;ajout de droit supl&#xe9;mentaire de mani&#xe8;re dynamique dans les extensions">
<node CREATED="1301881654613" ID="ID_85366718" MODIFIED="1301881666375" TEXT="trouv&#xe9; exemple pour aid&#xe9; a comprendre l&apos;utilit&#xe9;"/>
</node>
<node CREATED="1301881761531" ID="ID_1211766303" MODIFIED="1301881772116" TEXT="ajout&#xe9; une gestion des inscriptions">
<node CREATED="1301881779873" ID="ID_676081454" MODIFIED="1301881790619" TEXT="tout le monde peu s&apos;inscrire"/>
<node CREATED="1301881791512" ID="ID_1122806507" MODIFIED="1301881798950" TEXT="mod&#xe9;ration des inscrits"/>
<node CREATED="1301881803916" ID="ID_1497455619" MODIFIED="1301881810319" TEXT="inscription avec confirmation"/>
<node CREATED="1301882143388" ID="ID_1793661531" MODIFIED="1301882154254" TEXT="confirmation de l&apos;adresse email (optionel)"/>
<node CREATED="1301881811546" ID="ID_375816906" MODIFIED="1301881837401" TEXT="droit par d&#xe9;faut pour le nouveau inscrits, ceux en attente d&apos;etre valid&#xe9;, ceux valid&#xe9;">
<node CREATED="1301881837403" ID="ID_823915415" MODIFIED="1301882123645" TEXT="ajout&#xe9; un &#xe9;tat aux utilisateurs">
<node CREATED="1301881861362" ID="ID_1480175454" MODIFIED="1301881865925" TEXT="inscrit"/>
<node CREATED="1301881867412" ID="ID_341106234" MODIFIED="1301881871118" TEXT="valid&#xe9;"/>
<node CREATED="1301882082537" ID="ID_1955024875" MODIFIED="1301882085148" TEXT="inactif"/>
<node CREATED="1301882085854" ID="ID_1872860110" MODIFIED="1301882088380" TEXT="bannie"/>
<node CREATED="1301881873567" ID="ID_382041331" MODIFIED="1301881880941" TEXT="SOAP">
<node CREATED="1301882042006" ID="ID_1209956845" MODIFIED="1301882051542" TEXT="pour les acc&#xe8;s par service SOAP"/>
</node>
<node CREATED="1301881881958" ID="ID_152428440" MODIFIED="1301881884695" TEXT="action">
<node CREATED="1301882053319" ID="ID_267554688" MODIFIED="1301882067434" TEXT="pour les droits d&apos;acc&#xe8;s des action"/>
</node>
<node CREATED="1301881887860" ID="ID_1422918166" MODIFIED="1301881895975" TEXT="admin">
<node CREATED="1301881912086" ID="ID_1056948333" MODIFIED="1301881960382" TEXT="peut obligatoirement g&#xe9;r&#xe9; les droit d&apos;acc&#xe8;s et la config de l&apos;application"/>
</node>
<node CREATED="1301881888856" ID="ID_492502609" MODIFIED="1301881905375" TEXT="super-admin">
<node CREATED="1301881905376" ID="ID_1315267038" MODIFIED="1301881945697" TEXT="vois TOUT peu importe sa config d&apos;acc&#xe8;s"/>
</node>
<node CREATED="1301882090031" ID="ID_708180151" MODIFIED="1301882094909" TEXT="personalisable">
<node CREATED="1301882094910" ID="ID_1437964618" MODIFIED="1301882114505" TEXT="un &#xe9;tat peu &#xea;tre chang&#xe9; par task"/>
</node>
</node>
</node>
</node>
<node CREATED="1301881978214" ID="ID_558008639" MODIFIED="1301881995148" TEXT="loggu&#xe9; tout les changement fais sur les droit d&apos;acc&#xe8;s">
<node CREATED="1301881995149" ID="ID_1678561219" MODIFIED="1301882023950" TEXT="qui, quand, quoi, IP et autre info pertinante"/>
<node CREATED="1301882028874" ID="ID_1515072066" MODIFIED="1301882036075" TEXT="non effacable"/>
</node>
</node>
<node CREATED="1301880300138" ID="ID_1892697604" MODIFIED="1315061235950" POSITION="right" TEXT="task manager">
<node CREATED="1301880310552" ID="ID_475805414" MODIFIED="1301880318077" TEXT="(a renom&#xe9;)"/>
<node CREATED="1301882177687" ID="ID_683095498" MODIFIED="1301882227672" TEXT="doit permetre tout action automatis&#xe9;"/>
<node CREATED="1301882228337" ID="ID_512298148" MODIFIED="1301882239250" TEXT="se d&#xe9;clanche">
<node CREATED="1301882239251" ID="ID_851415141" MODIFIED="1301882261290" TEXT="par actions (une action peu invoqu&#xe9; directement un tache, peu servire de macro)"/>
<node CREATED="1301882261713" ID="ID_1797000292" MODIFIED="1301882269979" TEXT="par modification de donn&#xe9;e">
<node CREATED="1301882269980" ID="ID_380306594" MODIFIED="1301882306254" TEXT="g&#xe9;r&#xe9; les modification aussi pr&#xe9;cise qu&apos;a la colonne pres"/>
<node CREATED="1301882312303" ID="ID_1709953195" MODIFIED="1301882326676" TEXT="est d&#xe9;tect&#xe9; par le data layer"/>
</node>
<node CREATED="1301882340189" ID="ID_1656079649" MODIFIED="1301882372467" TEXT="manuelement par un utilisateur (revien a etre par action, mais permettre un moyen simple de le laiss&#xe9; y acc&#xe9;d&#xe9;"/>
</node>
<node CREATED="1301882585026" ID="ID_452331620" MODIFIED="1301882621012" TEXT="tout le monde pue cr&#xe9;&#xe9; des tache (enfin ses g&#xe9;r&#xe9; par le gestionnaire de droit)">
<node CREATED="1301882621013" ID="ID_177480333" MODIFIED="1301882645161" TEXT="un tache cr&#xe9;&#xe9; par un utilisateur obtient les m&#xea;me droit que lui"/>
<node CREATED="1301882663196" ID="ID_117913545" MODIFIED="1301882686887" TEXT="voire comment g&#xe9;r&#xe9; la visibilit&#xe9; de ces tache et leur condition d&apos;ex&#xe9;cution"/>
</node>
</node>
<node CREATED="1301879657804" ID="ID_521546054" MODIFIED="1315061232840" POSITION="right" TEXT="configuration">
<node CREATED="1301881165214" ID="ID_842976561" MODIFIED="1301881172096" TEXT="via fichier XML">
<node CREATED="1301881172097" ID="ID_1677110661" MODIFIED="1301881209906" TEXT="d&#xe8;s quil sera possible d&apos;utilis&#xe9; des fichier XML comme source de donn&#xe9;e nous aurons un &#xe9;diteur de config interne"/>
</node>
</node>
<node CREATED="1301879662386" ID="ID_679573232" MODIFIED="1315061231596" POSITION="right" TEXT="extension">
<node CREATED="1301881547971" ID="ID_247722007" MODIFIED="1301881571280" TEXT="doivent &#xea;tre simple a initialis&#xe9;/install&#xe9;"/>
<node CREATED="1301881572841" ID="ID_1970751999" MODIFIED="1301881591700" TEXT="doivent permetre l&apos;acc&#xe8;s a l&apos;ensemlbe des fonctionnalit&#xe9; de BeeERP">
<node CREATED="1301881591701" ID="ID_1068329957" MODIFIED="1301881600568" TEXT="donn&#xe9;"/>
<node CREATED="1301881601103" ID="ID_1908634209" MODIFIED="1301881605688" TEXT="gestion de droit">
<node CREATED="1301881605690" ID="ID_399751776" MODIFIED="1301881621599" TEXT="ajout de droit particulier, aps seulement sur les donn&#xe9;es"/>
</node>
<node CREATED="1301881675747" ID="ID_99173330" MODIFIED="1301881678986" TEXT="vue">
<node CREATED="1301881678987" ID="ID_214745843" MODIFIED="1301881689668" TEXT="par template (obligatoire)"/>
</node>
<node CREATED="1301881692411" ID="ID_1880365412" MODIFIED="1301881701470" TEXT="acc&#xe8;s SOAP"/>
</node>
</node>
</node>
</map>
