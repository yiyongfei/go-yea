<!-- Mainly scripts -->
<script src="${basepath}/js/jquery-2.1.1.js"></script>
<script src="${basepath}/js/bootstrap.min.js"></script>
<script src="${basepath}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${basepath}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<script src="${basepath}/js/inspinia.js"></script>
<script src="${basepath}/js/plugins/pace/pace.min.js"></script>

<script>
    String.prototype.endsWith=function(pattern){
	    var d=this.length-pattern.length;
	    return (d>=0 && this.lastIndexOf(pattern)==d)
	}

    if($("li.active").length > 0) {
        $("li.active").parents("li").addClass("active");
    }
    
</script>