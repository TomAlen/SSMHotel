<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.codecoord.com" prefix="c" %>
<c:forEach items="${thirdMenu}" var="thirdMenu">
   <a href="#" class="easyui-linkbutton" iconCls="${thirdMenu.icon }" onclick="${thirdMenu.url}" plain="true">${thirdMenu.name }</a>
</c:forEach>