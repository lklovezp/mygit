/**
 * �ַ�������
 * @author zn
 * 2013-03-25
 */

//���ַ�ת��Ϊǧ��λ��ʽ
function Convert(amtStr) {
    var a, renum = '';
    var j = 0;
    var a1 = '', a2 = '', a3 = '';
    var tes = /^-/;
    a = amtStr.replace(/,/g, "");
    a = a.replace(/[^-\.,0-9]/g, ""); //ɾ����Ч�ַ�
    a = a.replace(/(^\s*)|(\s*$)/g, ""); //trim
    if (tes.test(a)) a1 = '-';
    else a1 = '';
    a = a.replace(/-/g, "");
    if (a != "0" && a.substr(0, 2) != "0.") a = a.replace(/^0*/g, "");
    j = a.indexOf('.'); if (j < 0) j = a.length; a2 = a.substr(0, j); a3 = a.substr(j); j = 0;
    for (i = a2.length; i > 3; i = i - 3) {
        renum = "," + a2.substr(i - 3, 3) + renum;
        j++;
    }
    renum = a1 + a2.substr(0, a2.length - j * 3) + renum + a3;

    return renum;
}
//�����ļ�����['����','��׺']
function FilenameSplit(filename){
	if(filename==null||filename==''){//�ļ�����Ϊ��
	}else{		
		var index=filename.lastIndexOf('.');
		var name=filename.substring(0,index);
		var suffix=filename.substring(index+1);
		var arr=new Array();
		arr.push(name);
		arr.push(suffix);
		return arr;
	}
	return null;
}
function strToNum(str){
  if(null == str || "" == str){
    return "";
  }
  return parseFloat(str);
}
function getWidth(arr){
	return 800;
}