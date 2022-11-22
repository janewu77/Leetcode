package LeetCode;


import utils.comm;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * Example 2:
 *
 * Input: words = ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 * Example 3:
 *
 * Input: words = ["a",""]
 * Output: [[0,1],[1,0]]
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 5000
 * 0 <= words[i].length <= 300
 * words[i] consists of lower-case English letters.
 */

/**
 * H - [Time:  BruteForce: 30-
 *
 */
public class N336HPalindromePairs {

    public static void main(String[] args) {

        System.out.println(now());
        String[] data;

//        String str3 = new N336HPalindromePairs().reverseString("a");
//        String str1 = new N336HPalindromePairs().reverseString("abcd");
//        String str2 = new N336HPalindromePairs().reverseString("abcde");
//        System.out.println(str1);
//        System.out.println(str2);
//        System.out.println(str3);

//        boolean res1;
//        res1 = new N336HPalindromePairs().checkPalindromePairs("abc");
//        System.out.println(res1);
//        res1 = new N336HPalindromePairs().checkPalindromePairs("abba");
//        System.out.println(res1);


        data = new String[]{"a","aaa"};
        doRun("[[1, 0],[0, 1]]", data);

        data = new String[]{"a","aba"};
        doRun("[]", data);

        data = new String[]{"lls","s"};
        doRun("[[1, 0]]", data);

        data = new String[]{"a","b","c","ab","ac","aa"};
        doRun("[[5, 0],[3, 0],[4, 0],[1, 3],[2, 4],[0, 5]]", data);

        data = new String[]{"abcd","dcba","lls","s","sssll"};
        doRun("[[1, 0],[0, 1],[3, 2],[2, 4]]", data);

        data = new String[]{"lls","sssll"};
        doRun("[[0, 1]]", data);


        data = new String[]{"bat","tab","cat"};
        doRun("[[1, 0],[0, 1]]", data);

        data = new String[]{"aaa","aaaa","aaaaa"};
        doRun("[[1, 0],[2, 0],[0, 1],[2, 1],[0, 2],[1, 2]]", data);

        String exp = "[[2, 191],[2, 334],[36, 78],[36, 452],[37, 2],[37, 36],[37, 73],[37, 199],[37, 290],[37, 543],[37, 597],[52, 187],[59, 224],[59, 334],[59, 537],[62, 447],[69, 48],[69, 103],[69, 111],[69, 147],[69, 249],[69, 720],[69, 815],[69, 866],[73, 98],[73, 149],[78, 62],[78, 77],[78, 452],[78, 542],[78, 582],[78, 599],[103, 212],[103, 679],[107, 149],[107, 364],[111, 149],[111, 880],[130, 37],[130, 543],[130, 588],[133, 314],[133, 359],[133, 460],[133, 486],[133, 634],[147, 78],[147, 174],[149, 362],[149, 364],[149, 388],[149, 420],[149, 870],[160, 334],[172, 59],[172, 107],[172, 187],[172, 361],[172, 738],[187, 447],[187, 847],[191, 2],[191, 37],[191, 307],[191, 615],[191, 653],[191, 734],[197, 172],[197, 766],[199, 69],[199, 670],[212, 197],[212, 279],[212, 313],[212, 471],[212, 679],[214, 111],[224, 197],[232, 447],[232, 787],[234, 334],[249, 69],[253, 37],[272, 130],[272, 387],[272, 447],[286, 616],[290, 163],[290, 212],[304, 199],[307, 334],[313, 149],[314, 149],[334, 191],[334, 323],[334, 652],[334, 790],[359, 447],[359, 776],[362, 512],[362, 827],[364, 107],[364, 172],[364, 738],[371, 212],[374, 779],[388, 149],[388, 870],[414, 73],[414, 651],[418, 290],[420, 78],[426, 362],[432, 542],[444, 172],[447, 130],[447, 232],[447, 371],[447, 587],[447, 811],[452, 36],[452, 37],[458, 314],[460, 212],[460, 907],[471, 334],[471, 891],[486, 334],[486, 790],[486, 812],[497, 679],[509, 197],[511, 77],[511, 452],[512, 779],[514, 133],[542, 172],[542, 319],[543, 232],[573, 458],[581, 866],[582, 172],[585, 779],[587, 334],[587, 840],[597, 172],[599, 78],[615, 587],[632, 107],[634, 69],[634, 866],[651, 447],[652, 599],[672, 111],[679, 69],[679, 103],[734, 486],[766, 103],[779, 447],[779, 742],[787, 69],[790, 133],[790, 486],[811, 69],[815, 78],[847, 364],[866, 133],[866, 497],[866, 616],[866, 634],[870, 149],[870, 388],[880, 811],[891, 460],[898, 334],[907, 790],[918, 149]]";
        data = new String[]{"gdbhfjeg","ahjggc","hf","jjjaiaifhdijfa","bcefihd","eifafbihhffhhjd","icaficdaejgfadhdh","ifcaaajfhjahedhdic","cgcebfceficdbhjfi","jfc","jdegdfgajc","adcabijijejaea","egagffcfbecgcgjbcih","iafdi","djaaafdehacfe","gefhidifechgdeefdi","ahccgfdhbjiadcebeieh","aadadaddgchi","dbcaaddaaacaadjaeic","jegbfhaicihcijhdc","cefbaeifghhhdia","fdhbjgjfb","ejifid","bagd","aeebbdbfiaf","ghaeebjfg","ahijcjjfedae","jaaeaiaaigcegccf","jbibjhjahagejbad","jdbifadfjcdf","heccbdadegeff","iefjaj","ibbf","eahgfgbejib","hbedacjjjda","bihbeje","bf","f","jjdiefffgdabdidf","ejfhgcjehhfbaheeeda","cccgihheffaifea","gffbahcfcajdihfcbj","ccej","ijjgeihbfjfidaibbbca","jfhifcggighbgja","jfhbghe","jdeajahcbfgfdjecihei","iibgfeifd","jgjj","ebccde","fjfdebchhfbcbej","jaccebafaiij","gad","hejaee","idch","fiefaccchcegah","ibhdibgejia","ihjhgebaebicicbdcigc","ieehfbbbhiab","hg","bhabdbj","edjhe","ab","ajefdahfge","dcaaca","cheibjfcgiegh","dgjbhg","jhhjhbeh","ehgbegfjhdachehaafgc","j","jcabf","cjacejbeie","cfgbieghch","ef","gefc","gcgjabd","dgjifjgdffiah","fdfb","b","hcdaibdhbfif","cdg","jgeifai","dfgh","hcdfbhbdhcbjghh","jbjddehjbjdcigfcach","ifgg","gjhi","ghif","gdbibhgejbfcjiej","cgdb","ddeaahhibbhgjcjfbc","hcgfhdigebbgigjb","gehdfjhhhbbcjbccjh","hghcdedbe","bdehbbbeehcdhegh","adeaiigidejehae","hchhae","jhihfafb","hdbdhfe","ggejafebjiijge","eghjabiabafffccg","djhfif","djgjbjcffjcba","ij","hdiadajhc","fiehijciih","ihdgfadcdbcjicgjfehd","eg","dbafjdaififad","hjjdhgajfia","eieiiahighagch","ej","bbffcibjc","fcdfbfbh","jjggfihdjdja","abcajedbd","hiheceghdfgabjhjjejd","djaeeciaggja","diihcaeecbj","igiahecafhh","ijjfcffadc","ehbiegfcfacajjjdjhea","fgijbf","cffd","hcefcfbccfhficdd","hcfci","dhbafjfc","jjdih","fjifcfjejdhih","jhddcjfigcjede","fa","ihcfae","gedjfeehabjaib","d","hjch","hjahdaadg","cghaajcjfbicbjcifcfh","aajcifjijjibfc","daahccegihdadbabij","ibejihhadeeebcdh","gcdef","bfcjciefaajc","jabejh","feegfhceadggibajdige","agjjccgacgbhiadfjc","jaif","icj","bj","edc","e","idhif","gchcjcdahbee","ebafeibfdfhaadigcce","fjfbgcbihaa","higjibdbhh","heigfjfigbaajifei","addaeicbgaidgejeibd","efaaeef","jgbcge","edhhfbdefcd","hgg","jjceajbijbgicchh","dcjehifdhibjjb","efi","ccfdhfdehgh","jejbcadf","aeihhedh","bebicegbjfeche","fdifbd","hjjge","fchhbejhe","fciiiagbdegacbfdegda","g","hdbgiifheeidbdafh","ejb","fbid","ejjcai","bcafhiggigiff","eaaahicjddeijjijjjb","adiaaecfjeadb","adjbfgbhegichifead","ajfbfgdbccdacbfb","behabebgdbeii","gejjgdcdaj","digiifdaechjj","bjbghegghjjjf","dbfgijfecdjehcjai","ag","dbjdeigjejgbigibe","cbdbgfgcdhihddedbg","aeeeachjeif","fh","egbcefdff","bdhcaecc","cdc","gdjcegijh","bbfc","gi","gdhhijb","jf","hfcifjibgbdae","hjeiehibcaic","bghghicje","eibfgibbheagag","gabegejfg","dcjadcgeijfbjej","gfaihgbehffijjbj","ijjedigebiiagbj","ihebiadijbfigej","ccdjafjccjdgfhifgjc","gfhagciecih","fbjaf","i","bcaaihdbd","jeh","gchjjicejh","gbajidafd","aefihicehghgedagcijf","afifd","egcgfedcbaahdc","efghdaicbcjfjebfbjah","abijchcighgbdiebhjcb","jgefadchaaiiigchej","ahbhidfhijgcebhb","igh","cadhgjb","gaadaahegahjiabhhdb","ahjejchgidj","gaddcafgg","cacajjigjff","gfhae","diggigihbcceh","aa","ecfhbe","haea","acjigii","ffgbghbc","gfggdcciej","gifdfacdjj","iibgghecabc","aafggjchiiiidbi","cbcifi","jeggjffhidcfb","jiahdaihi","ceddabcgggjdjihgjdei","caggchhebhicbcf","jhhhf","faecfdcgfgjaibbdhj","hjiicjf","jj","ibdccdccdggjgcbd","bdgchgcdebfde","ghebcdadegj","fccc","aiba","giibbhhbhbibcfahf","gadifdgagcegjfcfiiab","dffeijgecd","abfiajddhj","hjhjf","agijdecjb","hbeiaadif","djjejddaibfieecbgg","cdaidfecd","aefbjccbjhehgb","gdf","iddibd","dafej","bjebgeeheaf","chgcaecjdjgd","fhiab","ghhj","aff","biheahjghcihfeefcge","hbibfe","hfjchchhgjbfaaa","ehcjdchf","igjahbjafaa","fjfdghhagbgaffgcc","adai","ibcdbcfeig","feefiiieceehg","cefcdhehig","fjiieed","aajfgfgjadiebebha","gjahhbigefggf","djgc","iifbjg","iihfcb","behgafaidhgcc","if","ijgeabijaiaahcfd","iihfh","djgjdhhfghcbhgbb","gdhbajfhiaj","jjdbbieagb","cfaefccb","dheiecjffifbdaje","acieheachdgea","fcgiabaeaeagh","dcaadf","ecefjabf","jhficihg","heci","fjgg","gjagfcaegjeabigggiff","aejif","hfhhf","iebcbi","jbei","eif","ffjjacghfagjbi","bfghhjadc","ei","ed","chjhbcfgg","dfadabgabfgdhfhdgeh","jgdahigd","hhaggbeegagihajhcg","ffbg","dcifdbjfebeddfgiig","iiifiejcce","fdjdbfe","cch","jcbhhe","fcfbhhjbedfdjhdiaj","giiacjdiegdhejgbaij","bejcdeach","bfgcjfhfciibcfdi","jjadbagfi","iabigbjg","cijjjiehgicjhcfiefg","iggieejhcifdhhjeg","dcjhgbdggj","h","jhgehafjbbgi","cjicja","icffajghfhbchicaa","dcidcdffagi","hjebihhgccbffe","cbjgagaje","dggafeaibaaigbfcdhgh","dcidgcgaahjjfja","fhchhh","fihcbgb","bifjj","icijbddj","gfdcibjieffb","dgejjiijgic","ejfgajcjieeigjgg","egejidighd","eijfjc","gdeeihgcedjhchagcgh","jcdbdccjg","hdhfej","ghijfghgcjeidefhbide","dcfgffcchdgfdbeg","ggdcjcaheffiacgg","eeice","ad","geacedccijfdaaajah","fhfg","ce","ffbfhgaada","ge","chjcebdfbg","ihadfbffgedb","fejcgeecfchjajfgeg","ahejidhghfdffegdg","bcjag","hgbeicjfeffi","ia","jbdaegehgaffhajagjdg","ageafhca","cag","fbdbdfifcgfigdechgif","ajcjbfaiagifabe","cbjjj","ggcafajddaieefb","fbacjhcdbe","hiegegigabghfgjh","cdhdhebh","ghfjhaa","dgegfeehchfjccfcdgjc","iegeaibeaebhdhaihjhb","cbia","chgbfdcdbjbg","cffa","eee","ddfhgfhjchfigdfiid","eiehhabe","bcbfbbhhhef","bgibcehdhc","bbigiddfdfji","iciaiijbefd","dhjbgjdbf","dcj","iaighbdbbjdhaiag","jhibe","jaehdcf","fajdgefadeiajbh","feifjaj","egajf","jbejcbcfcbdbgebeihb","jbjjbf","aadfgifcgfh","baecchded","icfffihcdi","dahjifdfg","icbcdbf","bchebdjc","bfbcigghdhb","bihaej","ijfiaefgf","fef","gcfdgfjffehfj","ibdbi","aajihjcididgccgddhc","fibb","fbgjdghajfhgigfhaech","be","jahhfhddiafdegagcj","eccddhcihb","jhabjfejaggd","jihbgfbhba","jjefejccefajbfifghca","ecg","bjdeaiehcjeehbhiibda","cijehiagafhhbecgdc","bcjbd","gahdejibafacdc","eaagdichgceehbd","bgfjf","ecfchdchfacaddjed","ijidb","ddajfbdahcicjbfaf","iaghbgiefedfhe","geedefefhidhdfcacaa","ahfcdhaffah","gihdagegdadbeeei","jebiebaheaaeidgiaf","ighdbibjgca","ahdjgadcfhhd","eecejd","gfdf","iigjddja","fcicchiajjffgbcia","a","bfddfgcjg","gbcdaeahcj","icjj","fjje","fb","bidejfhd","bbifehcjh","jccdeihj","hgdchhj","hbdijggagjha","deh","cgifhji","id","bdcichaiadggdha","eaiidfa","cggcjjhbjhd","egghdhc","eccgjaiheeggcihc","jbddad","eghd","iigbeiebhehc","cgfaefab","jbccidbhbdgiajiac","hi","ffbfdafiicdbgja","cdfaccadhjiagjeddjj","fch","eijdcidfegejbgjeh","adhehaadeeaib","gbgfhfaaegjedbgid","jieibfcfhcehaahdech","fbebcfabeccjjicccbd","fcdcjdgadiagaebibag","hejaieccfhdcafda","djdajfhgcefajg","ccgjgfjeei","ajebihbcgibjgaij","hbfbba","hd","cajhiafggjhbji","iigdgiijcebdbcgfh","ehjgaaehf","fiaedgehgbfd","dedfccb","hiccbibbeifaefggd","efbbfcfjcihb","gbgfadbfjieejf","ededbbhbahfe","ffchfhbjac","ijd","djggdb","hcijcdjefdbebafgde","ahbdddbegejeib","hbehajejiggegcbgecdd","acbghcjafh","ghechaifd","ebhigh","hhbggdbddb","hgghcc","fcfciedjeichccdf","afbiefheebd","igi","fejicaheca","bfd","c","ibhhbbbiggdaibgefhig","dhfh","baecedbafeefec","jehiicghccgfga","dgcecbajiebjhcgff","hagiffcfbibege","chdbdhghae","edbhdch","igdei","hhiihdjc","acjjjdffcbcgf","ajhfhgecg","bcbiejajjhaegabbdj","feegahbagc","gbcbbhefhbceggadhh","adbeffbgidbaiagibg","ehcgfagdidaeefdga","fbaccfhjadfhgbbfg","hfjhdeijihdga","jiajhcfga","jijdaiaachaeid","gbjajifff","haeaiebgggeiagjgg","cbifiigbecjecaceje","jgh","ibjaicbe","bjechjhha","fbceaghdcgbab","cgdjcfjbhdidecgejbgh","gb","aaf","bbhfiajgchejb","ijdahghhe","ebfeddhdjdiefa","ebcd","bbcfheibbfgjdbi","cdegaiagacfbdce","gahdfcj","aahbaebic","eihdhabj","aicgici","ibibbiacihbafdcaecif","chhfcgafffhhacdj","jhhjihbigfcehgjhafbi","djchihgjajbfijebcbe","gfjcgdig","fhfagcjcgdjj","jedddjagjiab","eigegcif","fiigeejhdhjgidgba","gbbehbadig","icbdfchiechbhggbdib","iebcj","ejeghbfhgaefbcad","cdbeejbfbf","dbhjcddfjfeihgae","icjaigiajajddfaaai","jgcadgif","jacdgiigajjba","edfaeiccbhhhdg","hedh","jcjifcjgjgiaijbhifbg","aahgdgc","hfifedgidiebbajddgef","jgcaebjhafbdda","ddiieebjfgeeaaeif","fbghhcfjgef","ccjgahhj","jdb","gbgb","gffiabeabjgbg","bfab","cad","hfjeied","ha","eaf","ejggfhfjahgicaic","aiaedffadefi","fahecaejcfg","hihacadhjbhaihbbj","ijciaejfafhddahdcdg","gaaejfhdfgdcegbi","fjgbjh","ddjgahbdbdbdejibib","gf","ebahafh","bb","fjefaghicbbb","gddj","ibegiechghjbdb","ecbfeajachdajgi","idbedagc","bgdeaeggcc","jieajhg","ehehchifddjagc","chgea","fdhcddadjcfid","hgibjghejedcffec","abjegabdbged","jejbjfdadchibdcbd","aihaeecdhjbe","ehiagdgecfaehebie","ahf","gjd","aifhchbacijbg","eihgbhc","ifiabehbjdd","ghgecdhj","gceecadjhdbgjfigf","acgciehafhba","abhcihdaid","gddiafjjjcbgc","bagjbffdeji","hdicejhdeagbcie","aebgbgffcjgccdgieg","jjgcidfifgbhbfagadgb","iaggda","iggcaiaig","cjfgffeagcg","gef","ifeiijcefcejbbiebe","jd","aagfbhj","igaebebh","agefjfjcdheifhiei","idbedhfebeaeeci","dedbgehjcedefab","idbcceadgcjii","bbfbiagjdhigigfg","dagdhjbaehddifdf","jgdjgjbchdiii","ahjgc","hcfhjiefceafefefc","ifeadhdci","cdagifddfjdhiaace","cjhfjeeiihac","ggbfbhaeiae","eicdjbdbdabjaaec","afef","bbh","ddhf","cbfge","ebgcdiebfhdjejhdfgh","afibhdahb","bbhgifahfjeddfgjcbcg","ajabbfaa","jgiaaabhdffieijfje","gjcbdgiebb","fgbddj","eaeicahfjghahggifa","cdfadcjjgcfj","biiddchf","bfgjbfeabccc","gjdcjbgdgabfidbfed","hgajjbcajjjadfbgh","hfjebhdb","iciagcdggd","efj","abgbjffjeccfgffdeei","jea","bfdc","hbihgg","ieagfejfgff","fgedgdgcggf","chdbigaeihggiighagc","hgagccgefggdihifi","ji","aifcebaeddfageejgj","jcbjafdjde","gaiafjbjbddcaf","ebcbjfada","diihefdecdeffefaba","haadjheidcaiiaedhfga","hcceagbihjfeafgi","hgafjgagihgf","gcbcdjecgibceajjgg","ajejdeigbjgggbhhe","igifgdiaj","iddbeehjdcadhhf","hhdhhegebecjfdef","ghddgdhffihfjdeibbg","gfhegbfhaeebjehf","cdaahiafifigaeddjabe","feigbhciibhideefgcbc","jcibaigfcahjfhbe","afidhddfgbaihgbbj","difeeabhfjaafgiaee","hijcicheejgd","aecdihffi","bdddchae","fajdgdfcdijhdbbbgch","hheiaaciabegfajhi","hijfgggje","befbdhajhiicgjagg","gbfebibiahghjh","ehdjgghhjfddca","ghiahiidic","ghhaadcjedei","bfhcjdahidejibiijigc","hicggehjicfegbigdi","cjjhbdjhigidai","dbccahehgd","accijjf","jhieificfcfcdchfde","jfffeddbaeijddcjijf","jcbeeadgebhah","eiedaggjaabajgjcf","ccj","abdfhhihahbfjacbjf","jjfbh","ehfbjfge","acaafiabcf","gdbei","eiacgjb","eghbhghjdi","edjad","eggiiidebcddb","hejcihbeec","fggecbebbciadcigfc","jhbdjbbgighgee","ibacgjaejebfc","dhf","ddabfeegchcihgaggh","ffbjgdc","difhec","eeg","ejgicaffhhhd","fhghdgaaaabebibabchi","jheehbggcafibacahfaa","ica","hab","fedeghfbjcicdhjfiaa","fiefgcahbi","bagf","cfdejaebbiaagc","jaijga","caijgdgbjfieiaig","eabfccjajdecdigefhga","dhbcgfgffgaaie","fgijicihb","hgfbcbhahfefh","efbihbajbdjfijdbbfjg","agfeechjdii","jgbdibdieiaffbad","hdiigja","jdhffbjgadbfajhcehj","ecgfefdigjcdijbhdfi","jeheaf","hgjgccjfhdbbhfchfigd","cieaahah","gjfceieibgfcbeifig","cgcahgcbej","ehacdjdfcaiadeh","jig","defbhdjedcdffi","ccadcehfgghehcc","figgdbbdgfehjhegc","bfhhbgbfbdc","hbjgddd","ghgcdefifjieeibbefh","dabadhegbe","hiaiac","agfaichhccegj","jjda","jcbeghai","fcbiddfachag","ac","agjiccejeibcaabe","cgfbjhhb","beajibcbggga","hdiciiddhifabjchicg","bbgcgdjgihb","hedjadfijiegicgfgh","bifabidc","jaa","gjffhahaddhjgdchcf","gjfifediiab","dh","cdjhcgfcaeebggegaj","igcedhagb","ieiiagf","jegfbci","agjgifieaejijcch","ahihaijdafaejbdhe","degajji","hfbfhhhcgchibiheaj","gefabc","fbeccgcee","fdhdgifdhjgiabdd","eibfjffdifjhciebiaj","bfddigheiifif","adedgcijjbheedccj","cbcjhab","dabje","adaiddijcb","hcahjcccfjghdf","egjdb","fdbjhhajbjbddjahbag","ja","bdh","efaibijdhijccab","feagibc","bjbj","fceaejg","igicd","jhjjdi","iaaeddbiidbigj","ahccbe","daahcdjdgbghjbagifi","aeahcbfadgehafhiha","hacajb","bdcbfi","deaecbdh","gbbbjccdhdeefeccace","aec","eibjeffbbgaefadbajfe","gfefjajfbihbjhjihj","iffd","bbbbgiiaecdbiggjgha","ecdfbgda","dgb","hbabddcbfjaeeg","iigfihdjhdebchfebbh","egja","bbajfha","hbhjjfbghebd","ahcbbcgeheaic","jah","gigfc","efaahdbfecdddffigfe","ajegijcceghbf","ejgc","edhbaiicdagfb","eceiefhfieeb","ega","iddedcaafeafejbjb","ihehgjddeifahaigf","dhfbgj","jbcfbgdfe","dfehihhfdhhb","afdaahibjcaji","jcjceiehiefgba","cegijcbefheiggg","abibaafjfiaiidh","fdfgfeahcdj","iacbhjhigjfceife","acdj","dedjcfdhcbbaafjdij","igjfbijbcdhbh","ddgfiagfgfgeaiefbe","aehjfifbhiihahjifg","abjhdaija","facf","dj","fdeahfjhdej","hia","jjbaabhjjfjjiihegh","eeee","fbhjdfd","hiaacff","efihgidiah","igebciibgj","hfcfbfheifieiddgb","abda","iccjgfjg","dehdbhddc","egejcfhcdfdegj","aje","dghhbfiaebiajgfdcjgb","jbjhjgdfdeegge","fjdghdehbjhbgjgc","fcjbacf","cjjciifcaic","faijbfbee","dehiicbdaahiah","adcbfcdjhddfdicbgih","fhfhajdcffedjjhd","dhfaajdbfadejjcaa","dih","eedgjdcabjbejhaggbha","egbdbdg","fbjigbdf","gfiiffdgbhb","dbfjfddchdigigc","jgcccgdehfijbci","hbib","igiefahig","ibhbciebc","cechah","efcfcheidbijihhadjg","ahfhgidjb","eafebahagci","hjdhbfifhejbacb","fehfdigd","hdi","ddfa","cddeh","bidgahidijjffi","dhddifgbidbghagj","haggghafbiehci","hdecajheeaddjdfjc","hdafei","eheejgahghcfjdjceidi","jhbceaahci","dahacagafbgaffigaide","ehfh","fcijagjhehgabdbegahj"};
        doRun(exp, data);

        data = new String[]{"a",""};
        doRun("[[0, 1],[1, 0]]", data);

        data = new String[]{"aba",""};
        doRun("[[0, 1],[1, 0]]", data);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String[] words) {
        List<List<Integer>> res1 = new N336HPalindromePairs().palindromePairs(words);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
        String res = comm.toString(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }



    //////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 231 ms, faster than 93.18% of Java online submissions for Palindrome Pairs.
    //Memory Usage: 213.7 MB, less than 45.29% of Java online submissions for Palindrome Pairs.
    //Tries
    //Time:O(N * W + N * N * W); Space:O(N * N + N * W * 26 + N * W)
    //Time:O(N * N * W); Space:O(N * N + N * W)
    //N is the number of inputs
    //W is the number of characters in the longest word.
    public List<List<Integer>> palindromePairs(String[] words) {
        //Space: O(N * N)
        List<List<Integer>> res = new ArrayList<>();

        //Time:O(N * W); Space:O(N * W * 26)
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) insert(root, words[i], i);

        //Time:O(N * N * W); Space:O(N * W)
        for (int i = 0; i < words.length; i++)
            if (!words[i].isEmpty())
                res.addAll(search(words, root, words[i], i));

        return res;
    }

    //Time : worst case : O(N * W ); Space: O(N * W)
    private List<List<Integer>> search(String[] words, TrieNode root, String word, int i){
        List<List<Integer>> res = new ArrayList<>();

        if (root.isEnd && isPalindrome(word,0, word.length() - 1)) {
            res.add(Arrays.asList(i, root.x));
            res.add(Arrays.asList(root.x, i));
        }

        TrieNode node = root;
        int j = word.length() - 1;

        while (j >= 0  && node != null){
            char c = word.charAt(j--);
            node = node.children[c -'a'];
            if (node != null && node.isEnd && node.x != i && isPalindrome(word,0, j))
                res.add(Arrays.asList(node.x, i));
        }

        //Time : worst case : O(N* W); Space: O(N*W)
        if (j < 0 && node != null){
            //DFS
            Deque<TrieNode> stack = new ArrayDeque<>();
            for (TrieNode trieNode : node.children)
                if (trieNode != null) stack.add(trieNode);
            int idx = word.length();
            while (!stack.isEmpty()) {
                TrieNode currNode = stack.pop();
                if (currNode.isEnd && currNode.x != i && isPalindrome(words[currNode.x], idx, words[currNode.x].length() - 1)) //.substring(idx)))
                    res.add(Arrays.asList(currNode.x, i));

                for (TrieNode trieNode : currNode.children)
                    if (trieNode != null) stack.add(trieNode);
            }
        }
        return res;
    }

    //Time:O(W/2)
    private boolean isPalindrome(String word, int left, int right){
        while (left < right)
            if (word.charAt(left++) != word.charAt(right--)) return false;
        return true;
    }

    //Time:O(W); Space:O(W * 26)
    private void insert(TrieNode root, String word, int idx){
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.children[c -'a'] == null)  node.children[c -'a'] = new TrieNode();
            node = node.children[c -'a'];
        }
        node.isEnd = true;
        node.x = idx;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        private boolean isEnd = false;
        private int x;
        public TrieNode() {
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 385 ms, faster than 66.41% of Java online submissions for Palindrome Pairs.
    //Memory Usage: 128 MB, less than 53.67% of Java online submissions for Palindrome Pairs.
    //Time:O(N * lgN + N * N * W); Space: O(N * N + N * W + N + W + W)
    //Time:O(N * N * W); Space: O(N * N + N * W)
    public List<List<Integer>> palindromePairs_2(String[] words) {
        //Space: O(N * N)
        List<List<Integer>> res = new ArrayList<>();

        //Time: O(N * lgN); Space: O(N * W + N)
        Map<String, Integer> map = new HashMap<>();
        Set<Integer> set = new TreeSet<>();
        int emptyStringIdx = -1;
        for (int i = 0; i < words.length; i++){
            if (words[i].isEmpty()) emptyStringIdx = i;
            else map.put(words[i], i);
            set.add(words[i].length());
        }

        //Time: O(N * W + N * N * W); Space: O(W + W)
        for (int i = 0; i < words.length; i++){
            String word = words[i];
            //Time：O(W); Space:O(W)
            String rWord = new StringBuilder(words[i]).reverse().toString();

            int j = map.getOrDefault(rWord, -1);
            if (j >= 0) {
                if (j != i) {
                    res.add(Arrays.asList(i, j));
                } else if (emptyStringIdx >= 0 && emptyStringIdx != i) {
                    //edge case: empty string
                    res.add(Arrays.asList(i, emptyStringIdx));
                    res.add(Arrays.asList(emptyStringIdx, i));
                }
            }

            //Time: O(N * W); Space:O(W)
            for (int idx : set) {
                if (idx >= word.length()) break;

                //suffix
                if (rWord.indexOf(word.substring(idx)) == 0){
                    String tmpS = rWord.substring(word.length() - idx);
                    if (map.containsKey(tmpS))
                        res.add(Arrays.asList(i, map.get(tmpS)));
                }

                //prefix
                if (word.indexOf(rWord.substring(idx)) == 0){
                    String tmpS = rWord.substring(0, idx);
                    if (map.containsKey(tmpS))
                        res.add(Arrays.asList(map.get(tmpS), i));
                }
            }
        }
        return res;
    }


    //翻转单词；然后N*N的拼接比对。
    //TLE
    //Brute Force
    //Time: O(N * W + N * N * W); Space:O(N * N + N * W + W + 8W)
    //Time: O(N * N * W); Space:O(N * N + N * W)
    //Let N be the number of words, and W be the length of the longest word.
    public List<List<Integer>> palindromePairs_1(String[] words) {
        //Space: O(N * N)
        List<List<Integer>> res = new ArrayList<>();

        //Space:O(N * W)
        String[] reverseWords = new String[words.length];

        //Time：O(N * W); Space:O(W)
        for (int i = 0; i < words.length; i++)
            reverseWords[i] = reverseString(words[i]);
        //reverseWords[i] = new StringBuilder(words[i]).reverse().toString();

        //Time: O(N * N * W); Space:O(8W)
        for (int i = 0; i < words.length; i++){
            for (int j = i + 1; j < words.length; j++){
                if (words[j].equals(reverseWords[i])){
                    res.add(Arrays.asList(i, j));
                    res.add(Arrays.asList(j, i));
                    continue;
                }
                //Time: O(W); Space:O(4 * 2 * W)
                if ((words[i] + words[j]).equals(reverseWords[j] + reverseWords[i]))
                    res.add(Arrays.asList(i, j));

                if ((words[j] + words[i]).equals(reverseWords[i] + reverseWords[j]))
                    res.add(Arrays.asList(j, i));
            }
        }
        return res;
    }

    //Time：O(W); Space:O(W)
    private String reverseString(String oldWord){
        char[] word = oldWord.toCharArray();

        for (int j = 0; j < word.length / 2; j++) {
            char tmp = word[j];
            word[j] = word[word.length - 1 - j];
            word[word.length - 1 - j] = tmp;
        }
        return String.valueOf(word);
    }

}
