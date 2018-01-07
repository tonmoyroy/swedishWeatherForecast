<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<title>EVe</title>
<link href="<c:url value='/resources/css/bootstrap.min.css'/>"
	rel="stylesheet">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/vendor/font-awesom/css/font-awesome.min.css'/>">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/vendor/mmenu/jquery.mmenu.all.css'/>" />
<!-- Menu Responsive -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/vendor/animate-wow/animate.css'/>">
<!-- Animation WOW -->

<link rel="stylesheet" type="text/css" media="screen"
	href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">


<link rel="stylesheet"
	href="<c:url value='/resources/css/vendor/labelauty/labelauty.css'/>">
<!-- Checkbox form Style -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/vendor/nouislider/nouislider.min.css'/>">
<!-- Slider Price -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/vendor/easydropdown/easydropdown.css'/>">
<!-- Select form Style -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/ui-spinner.css'/>">
<!-- Spinner -->

<link rel="stylesheet" href="<c:url value='/resources/css/menu.css'/>">
<!-- Include Menu stylesheet -->
<link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>">
<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/media-query.css'/>">
<!-- Media Query -->

<spring:url value="/resources/script/modernizr.min.js" var="modernizr" />
<script src="${modernizr}"></script>
<!-- Modernizr -->

</head>
<body class="fixed-header">
	<div id="page-container">
		<tiles:insertAttribute name="header" />

		<tiles:insertAttribute name="body" />
	</div>

	<tiles:insertAttribute name="footer" />

	<spring:url value="/resources/script/jquery.min.js" var="jquery" />
	<script src="${jquery}"></script>
	<!-- jQuery	(necessary for Bootstrap's JavaScript plugins) -->

	<spring:url value="/resources/script/jquery-ui.min.js" var="jqueryui" />
	<script src="${jqueryui}"></script>
	<!-- jQuery	UI is a	curated	set	of user	interface interactions,	effects, widgets, and themes -->

	<spring:url value="/resources/script/bootstrap.min.js"
		var="bootstrapmin" />
	<script src="${bootstrapmin}"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<spring:url value="/resources/script/vendor/mmenu/mmenu.min.all.js"
		var="mmenu" />
	<script src="${mmenu}"></script>
	<!-- Menu Responsive -->
	<spring:url value="/resources/script/vendor/animation-wow/wow.min.js"
		var="animation" />
	<script src="${animation}"></script>
	<!-- Animate Script	-->
	<spring:url value="/resources/script/vendor/labelauty/labelauty.min.js"
		var="labelauty" />
	<script src="${labelauty}"></script>
	<!-- Checkbox Script -->
	<spring:url value="/resources/script/vendor/parallax/parallax.min.js"
		var="parallax" />
	<script src="${parallax}"></script>
	<!-- Parallax Script -->
	<spring:url
		value="/resources/script/vendor/images-fill/imagesloaded.min.js"
		var="imagesloaded" />
	<script src="${imagesloaded}"></script>
	<!-- Loaded	image with ImageFill -->
	<spring:url
		value="/resources/script/vendor/images-fill/imagefill.min.js"
		var="imagefill" />
	<script src="${imagefill}"></script>
	<!-- ImageFill Script -->
	<spring:url
		value="/resources/script/vendor/easydropdown/jquery.easydropdown.min.js"
		var="easydropdown" />
	<script src="${easydropdown}"></script>
	<!-- Select	list Script	-->
	<spring:url
		value="/resources/script/vendor/carousel/responsiveCarousel.min.js"
		var="carousel" />
	<script src="${carousel}"></script>
	<!-- Carousel Script -->


	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAnQ1zilgA9KAV7a93YTPhBR9ij0cbyg9M&libraries=places"></script>
	<!-- Google Map -->

	<spring:url value="/resources/script/vendor/maps/infobox.js"
		var="infobox" />
	<script src="${infobox}"></script>
	<!-- InfoBox extends the Google Maps JavaScript API V3 -->
	<spring:url value="/resources/script/vendor/maps/home-maps.js"
		var="homemaps" />
	<script src="${homemaps}"></script>
	<!-- Custtom Map Color / Box Property -->
	<spring:url value="/resources/script/vendor/maps/markerclusterer.js"
		var="markerclusterer" />
	<script src="${markerclusterer}"></script>
	<!-- The library creates and manages per-zoom-level clusters for large amounts of markers -->


	<spring:url value="/resources/script/custom.js" var="custom" />
	<script src="${custom}"></script>
	<!-- Custom	Script -->


	<spring:url value="/resources/script/vendor/maps/chart.js" var="chart" />
	<script src="${chart}"></script>

	<spring:url value="/resources/script/vendor/maps/chart.bundle.js"
		var="chartbundle" />
	<script src="${chartbundle}"></script>
	
	<spring:url value="/resources/script/vendor/maps/utils.js"
		var="utils" />
	<script src="${utils}"></script>


</body>
</html>