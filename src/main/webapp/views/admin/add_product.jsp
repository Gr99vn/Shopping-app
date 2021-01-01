<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Add Product</title>
    <script src="<c:url value= '/templates/admin/js/ajaxSendDataToApi.js' />"></script>
    <script src="<c:url value= '/templates/admin/js/addProduct.js' />"></script>
  </head>
  <body>
    <div class="main-content main-fit">
      <div id="product-form">
        <form class="form-left" data-url="<c:url value='/api-admin-product'/>">
          <label for="name">Name</label><br />
          <input
            class="mb-20"
            type="text"
            id="name"
            name="name"
            autocomplete="off"
            size="40"
          /><br />
          <label for="price" class="mr-10">Price</label>
          <input
            class="mb-20 mr-10"
            type="text"
            id="price"
            name="price"
            autocomplete="off"
          />
          VND<br />
          <input
            type="hidden"
            id="defaultImg"
            name="defaultSource"
            value=""
            placeholder="Default image link"
          />
          <label for="file1">Default image</label>
          <input
            class="mb-20"
            type="file"
            id="file1"
            name="defaultSource"
            accept="image/png, image/jpeg"
          />
          <br />
          <label for="description">Description</label><br />
          <textarea
            class="mb-20"
            id="description"
            rows="8"
            cols="42"
          ></textarea>
          <input type="hidden" name="des" id="des" />
          <button class="form-submit" data-method="POST">Create Product</button>
        </form>
        <div class="form-right dspnone">
          <form
            class="color-source-form"
            data-url="<c:url value='/api-admin-color-source'/>"
          >
            <input id="product-id" type="hidden" name="productId" value="" />
            <label for="colors" class="mr-10">Color</label>
            <select name="colorId" id="colors" class="mr-10">
              <c:forEach var="color" items="${COLORS}" varStatus="loop">
                <option value="${color.getId()}">${color.getName()}</option>
              </c:forEach>
            </select>
            <input type="hidden" name="colorId" id="color-id" />
            <input type="hidden" id="imglink" name="source" />
            <label for="file2" class="upload-file-btn"> Image </label>
            <input type="file" id="file2" name="source" />
            <button class="add-product-color" data-method="POST">Add</button>
          </form>
          <div class="color-source mt-10"></div>
        </div>
      </div>
    </div>
  </body>
</html>
