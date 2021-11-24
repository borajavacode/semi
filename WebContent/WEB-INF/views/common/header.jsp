<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<meta content="width=device-width" name="viewport">
<!-- 부트스트랩 CSS -->
<link rel="stylesheet" href="/css/bootstrap.css">
<!-- 글꼴적용(NotoSans 폰트) -->
<link rel="stylesheet" href="/css/font.css">
<!-- 기본 CSS -->
<link rel="stylesheet" href="/css/default.css">


<!-- HEADER CSS -->
<link rel="stylesheet" href="/css/header.css">

<!-- jQuery라이브러리 추가(2개) -->
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<!-- 부트스트랩용 jQuery -->
<script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>




</style>
<!-- ======= Header ======= -->
<header id="header" class="d-flex align-items-center">
	<div class="container d-flex align-items-center justify-content-between">
		
		<div class="logo">
			<a href="/"><img src="/img/logo/kh-escape-logo-bb.png" class="img-fluid"></a>
		</div>
		

		<nav id="navbar" class="navbar">
		
    
			<ul>
				<li><a class="nav-link scrollto active" href="/">HOME</a></li>
				<li><a class="nav-link scrollto theme" href="/themaList">THEMA</a></li>
				<li><a class="nav-link scrollto" href="/reservation">RESERVATION</a></li>
				<li class="dropdown"><a href="#"><span>COMMUNITY</span></a>
					<ul>
						<li><a href="/noticeList?reqPage=1">공지사항</a></li>
						<li><a href="/eventList?reqPage=1">이벤트</a></li>
						<li><a href="/boardList?reqPage=1&listFind=1">자유게시판</a></li>
						<li><a href="/photoList?reqPage=1">포토갤러리</a></li>
						<li><a href="/faqList?reqPage=1">Q&A</a></li>
					</ul>
				</li>
				<li><a class="nav-link scrollto" href="/location">LOCATION</a></li>
				<li style="margin-right:250px;"></li>
				<c:choose>
					<c:when test="${empty sessionScope.m }">
						<li  class="dropdown"><a href="#"><img src="/img/icon/logout.png" style="width:50px;"><span class="mp">MYPAGE</span></a>
							<ul style="padding:0;">
								<li id="mypage">마이페이지는<p><b>회원 전용</b> 메뉴입니다.</li>
								<li style="padding-left:15px;"><a id="headerBtn" href="/loginFrm">LOGIN</a></li>
								<li style="padding-left:15px;"><a id="headerBtn" href="/joinFrm">JOIN</a></li>
							</ul>
						</li>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${sessionScope.m.memberLevel eq 1 }">
								<li class="dropdown"><a href="#"><img src="/img/icon/login.gif" style="width:50px;"><span class="mp">MYPAGE</span><i class="bi bi-chevron-down"></i></a>
							<ul style="padding:0;">
								<li style="padding-left:15px;"><a id="headerBtn" href="/adminPage"><span class="mp">ADMIN PAGE</span></a></li>
								<li style="padding-left:15px;"><a id="headerBtn" href="/logout">LOGOUT</a></li>
							</ul>
						</li>
							</c:when>
							<c:when test="${sessionScope.m.memberLevel eq 2 }">
								<li class="dropdown"><a href="#"><img src="/img/icon/login.gif" style="width:50px;"><span class="mp">MYPAGE</span></a>
							<ul style="padding:0;">
								<li style="padding-left:15px;"><a id="headerBtn" href="/myPage">MY INFO</a></li>
								<li style="padding-left:15px;"><a id="headerBtn" href="/logout">LOGOUT</a></li>
							</ul>
						</li>
							</c:when>
						</c:choose>
					</c:otherwise>
			</c:choose>
			</ul>
			<c:choose>
			<c:when test="${empty sessionScope.m }">
			<img id="123" src="/img/icon/logout.png" style="width:50px;"><label for="123"><a class="mobile-nav-toggle">Menu</a></label>
			</c:when>
			<c:otherwise>
			<img id="456" src="/img/icon/login.gif" style="width:50px;"><label for="456"><a class="mobile-nav-toggle">Menu</a></label>
			</c:otherwise>
			</c:choose>
		</nav>
		<!-- .navbar -->

	</div>
	
   <script>
   (function() {
	   $(".theme").mouseover(function(){
		  $(this).html("THEME"); 
	   });
	   $(".theme").mouseleave(function(){
			  $(this).html("THEMA"); 
		   });
        "use strict";

        /**
         * Easy selector helper function
         */
        const select = (el, all = false) => {
          el = el.trim()
          if (all) {
            return [...document.querySelectorAll(el)]
          } else {
            return document.querySelector(el)
          }
        }

        /**
         * Easy event listener function
         */
        const on = (type, el, listener, all = false) => {
          let selectEl = select(el, all)
          if (selectEl) {
            if (all) {
              selectEl.forEach(e => e.addEventListener(type, listener))
            } else {
              selectEl.addEventListener(type, listener)
            }
          }
        }

        /**
         * Easy on scroll event listener 
         */
        const onscroll = (el, listener) => {
          el.addEventListener('scroll', listener)
        }

        /**
         * Navbar links active state on scroll
         */
        let navbarlinks = select('#navbar .scrollto', true)
        const navbarlinksActive = () => {
          let position = window.scrollY + 200
          navbarlinks.forEach(navbarlink => {
            if (!navbarlink.hash) return
            let section = select(navbarlink.hash)
            if (!section) return
            if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
              navbarlink.classList.add('active')
            } else {
              navbarlink.classList.remove('active')
            }
          })
        }
        window.addEventListener('load', navbarlinksActive)
        onscroll(document, navbarlinksActive)

        /**
         * Scrolls to an element with header offset
         */
        const scrollto = (el) => {
          let header = select('#header')
          let offset = header.offsetHeight

          let elementPos = select(el).offsetTop
          window.scrollTo({
            top: elementPos - offset,
            behavior: 'smooth'
          })
        }

        /**
         * Header fixed top on scroll
         */
        let selectHeader = select('#header')
        if (selectHeader) {
          let headerOffset = selectHeader.offsetTop
          let nextElement = selectHeader.nextElementSibling
          const headerFixed = () => {
            if ((headerOffset - window.scrollY) <= 0) {
              selectHeader.classList.add('fixed-top')
              
            } else {
              selectHeader.classList.remove('fixed-top')
              
            }
          }
          window.addEventListener('load', headerFixed)
          onscroll(document, headerFixed)
        }

        /**
         * Mobile nav toggle
         */
        on('click', '.mobile-nav-toggle', function(e) {
          select('#navbar').classList.toggle('navbar-mobile')
          
        })

        /**
         * Mobile nav dropdowns activate
         */
        on('click', '.navbar .dropdown > a', function(e) {
          if (select('#navbar').classList.contains('navbar-mobile')) {
            e.preventDefault()
            this.nextElementSibling.classList.toggle('dropdown-active')
          }
        }, true)

        /**
         * Scrool with ofset on links with a class name .scrollto
         */
        on('click', '.scrollto', function(e) {
          if (select(this.hash)) {
            e.preventDefault()

            let navbar = select('#navbar')
            if (navbar.classList.contains('navbar-mobile')) {
              navbar.classList.remove('navbar-mobile')
              let navbarToggle = select('.mobile-nav-toggle')
              
            }
            scrollto(this.hash)
          }
        }, true)

        /**
         * Scroll with ofset on page load with hash links in the url
         */
        window.addEventListener('load', () => {
          if (window.location.hash) {
            if (select(window.location.hash)) {
              scrollto(window.location.hash)
            }
          }
        });
      })()
   </script>
</header>
<!-- End Header -->
<script src="/js/main.js"></script>
