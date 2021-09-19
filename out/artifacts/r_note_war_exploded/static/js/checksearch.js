function isempty(str) {
    if (str == null && str.trim() == "") {
        return true;
    }
    return false;
}

function checksearch() {
    let search_name = $("#search_by_name").val();
    if(isempty(search_name)){
        //如果为空那么在对应的位置加上提示的信息

        return ;
    }

}