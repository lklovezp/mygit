function text12(obj) {
    var value = obj.id;
    var arr = new Array();
    arr = value.split(",");
    if (arr[1] == 'doc' || arr[1] == 'docx' || arr[1] == 'xlsx') {
        window.location.href = 'docpreview.htm?id=' + arr[0];
    } else if (arr[1] == 'jpg' || arr[1] == 'png' || arr[1] == 'bmp' || arr[1] == 'jpeg') {
        window.location.href = 'imgView.htm?id=' + arr[0];
    } else if (arr[1] == 'pdf') {
        window.location.href = 'pdfView.htm?id=' + arr[0];
    }
}