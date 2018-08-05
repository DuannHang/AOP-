
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!--百度编辑器的语法高亮  start -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript">
	SyntaxHighlighter.all();
</script>
<!--百度编辑器的语法高亮   end-->



<div class="data_list">
	<div class="data_list_title">
		<img
			src="${pageContext.request.contextPath}/static/images/blog_show_icon.png" />
		博客内容
	</div>
	<div>
		<div class="blog_title" align="center">
			<h3>
				<strong>${blog.title }</strong>
			</h3>
		</div>
		<div class="blog_share">
			<div class="bshare-custom">
				<a title="分享到QQ空间" class="bshare-qzone"></a><a title="分享到新浪微博"
					class="bshare-sinaminiblog"></a><a title="分享到人人网"
					class="bshare-renren"></a><a title="分享到腾讯微博" class="bshare-qqmb"></a><a
					title="分享到网易微博" class="bshare-neteasemb"></a><a title="更多平台"
					class="bshare-more bshare-more-icon more-style-addthis"></a><span
					class="BSHARE_COUNT bshare-share-count">0</span>
			</div>
			<script type="text/javascript" charset="utf-8"
				src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script>
			<script type="text/javascript" charset="utf-8"
				src="http://static.bshare.cn/b/bshareC0.js"></script>
		</div>


		<div class="blog_info" align="center">
			发布时间：『
			<fmt:formatDate value="${blog.releaseDate }" type="date"
				pattern="yyyy-MM-dd HH:mm" />
			』&nbsp;&nbsp; 博客类别：${blog.blogType.typeName }&nbsp;&nbsp;阅读(${blog.clickHit })&nbsp;&nbsp;评论(${blog.replyHit })
		</div>
		<div class="blog_content">${blog.content }</div>
		
		<div class="blog_keyWord">
			<font><strong>关键字:</strong></font>
			<c:choose>
				<c:when test="${keyWord==null }">&nbsp;&nbsp;&nbsp;无</c:when>
				<c:otherwise>
					<c:forEach items="${keyWord }" var="word">
						&nbsp;&nbsp;<font size="5px"><a href="#" target="_blank">${word }</a></font>&nbsp;&nbsp;
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="blog_lastAndNextPage" style="padding-top: 20px;">
			${pageCode }
		</div>
		
		
	</div>

</div>
