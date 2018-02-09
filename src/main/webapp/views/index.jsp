<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.unionx.wantuo.model.Manage" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Manage user=(Manage)request.getSession().getAttribute("userinfo");
String loginAccounts=user.getManage_name();
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <link href="<%=basePath %>css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>css/style.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>js/jquery-1.7.1.min.js"></script>
    
    <script type="text/javascript" src="<%=basePath %>lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>lib/layer/1.9.3/layer.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/H-ui.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/H-ui.admin.js"></script>

    <title>爱晚托管理系统平台</title>
<style type="text/css">
.Hui-header{background-color:#fca31e;}
.Hui-aside{background-color:#FFF;}
</style>
</head>
<body>
    <header class="Hui-header cl">
        <a class="Hui-logo l" title="H-ui.admin v2.3">爱晚托管理系统平台</a>
        <ul class="Hui-userbar">
            <li class="dropDown dropDown_hover"><a href="#" class="dropDown_A"><%=loginAccounts %> <i class="Hui-iconfont">&#xe6d5;</i></a>
                <ul class="dropDown-menu radius box-shadow">
                    <li><a href="<%=basePath %>login/loginOut">退出</a></li>
                </ul>
            </li>
             <li>
                <ul class="dropDown-menu radius box-shadow">
                    <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                    <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                    <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                    <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                    <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                    <li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
                </ul>
            </li>
        </ul>
        <a aria-hidden="false" class="Hui-nav-toggle" href="#"></a>
    </header>
    <aside class="Hui-aside">
        <input runat="server" id="divScrollValue" type="hidden" value="" />
        <div class="menu_dropdown bk_2">	
            <dl id="Dl6">
                <dt><i class="Hui-iconfont">&#xe616;</i><a _href="<%=basePath %>views/jigouguanli.jsp" href="javascript:void(0)"> 机构管理</a><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            </dl>
             <dl id="menu-order">
                <dt><i class="Hui-iconfont">&#xe62d;</i>  <a _href="<%=basePath %>views/xuexiaoguanli.jsp" href="javascript:void(0)">学校管理</a><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                
            </dl>
          <dl id="menu-order">
                <dt><i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <li><a _href="<%=basePath %>views/jigouguanli.jsp" href="javascript:void(0)"></a></li>
                        <li><a _href="<%=basePath %>views/xitongchaxun.jsp" href="javascript:void(0)"><img src="<%=basePath %>images/usermange.png" width="17" height="17" /> 用户管理</a></li>
                        <li><a _href="<%=basePath %>views/shujuzidian.jsp" href="javascript:void(0)"><img src="<%=basePath %>images/dinctionary.png" width="17" height="17" /> 数据字典</a></li>

                       
                    </ul>
                </dd>
            </dl>
            <dl id="menu-order">
                <dt><i class="Hui-iconfont">&#xe622;</i> <a _href="<%=basePath %>views/fankuiguanli.jsp" href="javascript:void(0)">反馈管理</a><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                
            </dl>
            
             <dl id="menu-order">
                <dt><i class="Hui-iconfont">&#xe620;</i> <a _href="<%=basePath %>views/tuisongguanli.jsp" href="javascript:void(0)">推送管理</a><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                
            </dl>
              <dl id="menu-order">
                <dt><i class="Hui-iconfont">&#xe60d;</i> <a _href="<%=basePath %>views/jiazhangguanli.jsp" href="javascript:void(0)">家长管理</a><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                
            </dl>
             <dl id="menu-order">
                <dt><i class="Hui-iconfont">&#xe613;</i> <a _href=<%=basePath %>views/pingjiaguanli.jsp>评价管理</a><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                
            </dl>
             <dl id="menu-order">
                <dt><i class="Hui-iconfont">&#xe613;</i> <a _href=<%=basePath %>views/paihangbang.jsp>排行榜</a><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                
            </dl>
            <dl id="menu-order">
                <dt><i class="Hui-iconfont">&#xe613;</i> <a _href=<%=basePath %>views/guanliyuanrizhi.jsp>管理员日志</a><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                
            </dl>
            <dl id="menu-order">
                <dt><i class="Hui-iconfont">&#xe62e;</i> 报表<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <li><a _href="jigoubjbiao.jsp" href="javascript:void(0)">机构班级报表</a></li>
                        <li><a _href="jigounjbiao.jsp" href="javascript:void(0)">机构年级报表</a></li>
                        <li><a _href="xueshengqiandaobiao.jsp" href="javascript:void(0)">机构学生签到报表</a></li>
                    </ul>
                </dd>
            </dl>
            <dl id="menu-order">
                <dt><i class="Hui-iconfont">&#xe613;</i> <a _href="<%=basePath%>activity/QueryList">活动管理</a><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            </dl>
        </div>
    </aside>
    <div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onclick="displaynavbar(this)"></a></div>
    <section class="Hui-article-box">
        <div id="Hui-tabNav" class="Hui-tabNav">
            <div class="Hui-tabNav-wp">
                <ul id="min_title_list" class="acrossTab cl">
                    <li class="active"><span title="咨询首页" data-href="welcome.jsp">咨询首页</span><em></em></li>
                </ul>
            </div>
            <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
        </div>
        <div id="iframe_box" class="Hui-article">
            <div class="show_iframe">
                <div style="display: none" class="loading"></div>
                <iframe scrolling="yes" frameborder="0" src="<%=basePath %>views/welcome.jsp"></iframe>
            </div>
        </div>
    </section>

    <script type="text/javascript">
        /*资讯-添加*/
        function article_add(title, url) {
            var index = layer.open({
                type: 2,
                title: title,
                content: url
            });
            layer.full(index);
        }
        /*图片-添加*/
        function picture_add(title, url) {
            var index = layer.open({
                type: 2,
                title: title,
                content: url
            });
            layer.full(index);
        }
        /*产品-添加*/
        function product_add(title, url) {
            var index = layer.open({
                type: 2,
                title: title,
                content: url
            });
            layer.full(index);
        }
        /*用户-添加*/
        function member_add(title, url, w, h) {
            layer_show(title, url, w, h);
        }
    </script>
    <script type="text/javascript">
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s)
        })();
        var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
        document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
    </script>

    <script type="text/javascript">
        $(function () {
            $(".menu_dropdown dl>dt").unbind().click(function () {
                if ($(this).attr("class") == "selected") {
                    $(this).removeClass("selected").parent().children("dd").css({ "display": "none" });
                } else {
                    $(this).parent().parent().children("dl").children("dt").removeClass("selected").parent().children("dd").css({ "display": "none" });
                    $(this).addClass("selected").parent().children("dd").css({ "display": "block" });
                }
            });

        })
    </script>
</body>
</html>
