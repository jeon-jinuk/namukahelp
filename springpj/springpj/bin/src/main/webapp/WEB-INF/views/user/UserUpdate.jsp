<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org" layout:decorator="layout">

    <th:block layout:fragment="content">
    <!--/* 검색 영역 */-->
    <div class="input-group" id="adv-search">
        <select id="searchType" class="form-control" style="width: 100px;">
            <option value="">전체</option>
            <option value="username">이름</option>
            <option value="update">수정</option>
            <option value="delete">삭제</option>
        </select>
        <input type="text" id="searchKeyword" class="form-control" placeholder="키워드를 입력해 주세요." style="width: 300px;" />
        <button type="button" class="btn btn-primary">
            <span aria-hidden="true" class="glyphicon glyphicon-search"></span>
        </button>
    </div>

    <!--/* 게시글 영역 */-->
    <div class="table-responsive clearfix">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>고유번호</th>
                    <th>이름</th>
                    <th>닉네임</th>
                    <th>수정</th>
                    <th>삭제</th>
                </tr>
            </thead>

            <!--/* 게시글 리스트 Rendering 영역 */-->
            <tbody id="list">

            </tbody>
        </table>
        <div class="btn_wrap text-right">
            <a class="btn btn-primary waves-effect waves-light">Write</a>
        </div>

        <!-- 페이지네이션 Rendering 영역 -->
        <nav>

        </nav>
    </div>
    </th:block>


    <th:block layout:fragment="script">
    <script th:inline="javascript">
	/*<![CDATA[*/

		

	/*]]>*/
	
	<input type="button" name="UserUpdate" id="UserUpdate">
	<input type="button" name="UserDelete" id="UserDelete"> 
    </script>
    </th:block>

</html>