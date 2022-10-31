<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
					<p><span><strong>총 ${comment.totalCount }개</strong>  |  ${commentVO.page }/${comment.totalPage }페이지</span></p>
                    <table class="list">
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="200px" />
                        </colgroup>
                        <tbody>
						
						<c:if test="${empty comment.list }">
                            <tr>
                                <td class="first" colspan="8">등록된 글이 없습니다.</td>
                            </tr>
						</c:if>

						<c:forEach var="cvo" items="${comment.list }">
                            <tr>
                                <td>${cvo.no }</td>
                                <td class="txt_l" style="text-align: left">
                                    ${cvo.content }
                                </td>
                                <td class="writer">
                                     ${cvo.member_name }
                                </td>
                                <td class="date">
                                	<fmt:formatDate value="${cvo.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                	<c:if test="${cvo.member_no == loginInfo.no }">
                                	&nbsp &nbsp 
                                	<button type="button" onclick="javascript:commentDel(${cvo.no})">[삭제]</button>
                                	</c:if>
                                </td>
                            </tr>
						</c:forEach>
                        </tbody>
                    </table>
	                    <div class="pagenate clear">
	                        <ul class='paging'>
	                        
	                        <c:if test="${comment.prev }">
								<li><a href="javascript:getComment(${comment.startPage -1})"><<</a>
							</c:if>
	                        
	                        <c:forEach var="idx" begin="${comment.startPage }" end="${comment.endPage }">
	                        	<li><a href="javascript:getComment(${idx })" <c:if test="${idx==commentVO.page}"> class="current"</c:if>>${idx }</a></li>
	                        </c:forEach>
	                        
	                        <c:if test="${comment.next }">
								<li><a href="javascript:getComment(${comment.endPage +1})">>></a>
							</c:if>
	                        </ul> 
	                    </div>