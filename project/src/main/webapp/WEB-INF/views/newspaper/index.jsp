<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>게시판목록</title>
    <link rel="stylesheet" href="/project/css/reset.css"/>
    <link rel="stylesheet" href="/project/css/contents.css"/>
    <script>
    	function submit() {
    		var a = document.querySelector("form[name='searchForm']");
    		a.submit;
    	}
    </script>
</head>
<body>
        <div class="sub">
            <div class="size">
                <h3 class="sub_title">게시판</h3>
    
                <div class="bbs">
                    <table class="list">
                    <p><span><strong>총 ${data.totalCount }개</strong>  |  ${newsPaperVO.page }/${data.totalPage }페이지</span></p>
                        <caption>게시판 목록</caption>
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />	<!--  *은 다른 설정값을 제외한 나머지값으로 알아서 설정됨 -->
                            <col width="150px" />
                            <col width="100px" />
                            <col width="200px" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>조회수</th>
                                <th>작성자</th>
                                <th>작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty data.list }">
                            <tr>
                                <td class="first" colspan="5">등록된 글이 없습니다.</td>
                            </tr>
                        </c:if>
							
						<c:forEach items="${data.list }" var="vo" varStatus="status">
							<tr>
                                <td>${data.totalCount - status.index -(newsPaperVO.page -1) * newsPaperVO.rowPerPage }</td>  <!-- 총 개수 - 인덱스 - (현재 페이지번호 -1) * 페이지당 개수 -->
                                <td class="txt_l">
                                    <a href="view.do?no=${vo.no }">${vo.title }</a>
                                </td>
                                <td class="viewcount">
                                	${vo.viewcount }
                                </td>
                                <td class="writer">
                                    ${vo.member_no}
                                </td>
                                <td class="date"><fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                            </tr>
						</c:forEach>
                                    
                        </tbody>
                    </table>
                    <div class="btnSet"  style="text-align:right;">
                        <a class="btn" href="write.do">글작성 </a>
                    </div>
                    <div class="pagenate clear">
                        <ul class='paging'>
                        
                        <c:if test="${data.prev }">
							<li><a href="index.do?page=${data.startPage -1}&stype=${param.stype}&sword=${param.sword}"><<</a>
						</c:if>
                        
                        <c:forEach var="idx" begin="${data.startPage }" end="${data.endPage }">
                        	<li><a href="index.do?page=${idx }&stype=${param.stype}&sword=${param.sword}" <c:if test="${idx==newsPaperVO.page}"> class="current"</c:if>>${idx }</a></li>
                        </c:forEach>
                        
                        <c:if test="${data.next }">
							<li><a href="index.do?page=${data.endPage +1}&stype=${param.stype}&sword=${param.sword}">>></a>
						</c:if>
                        </ul> 
                    </div>
                
                    <!-- 페이지처리 -->
                    <div class="bbsSearch">
                        <form method="get" name="searchForm" id="searchForm" action="index.do">
                            <span class="srchSelect">
                                <select id="stype" name="stype" class="dSelect" title="검색분류 선택">
                                    <option value="all">전체</option>
                                    <option value="title">제목</option>
                                    <option value="contents">내용</option>
                                </select>
                            </span>
                            <span class="searchWord">
                                <input type="text" id="sword" name="sword" value="${data.sword }"  title="검색어 입력">
                                <input type="button" id="" value="검색" title="검색" onclick="submit()">
                            </span>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>
        
</body>
</html>