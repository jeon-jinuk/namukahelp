<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link
            rel="canonical"
            href="https://getbootstrap.com/docs/5.0/examples/dashboard/"
        />

        <!-- Bootstrap core CSS -->
        <link href="/css/bootstrap.min.css" rel="stylesheet" />
        <!-- <link href="css/index.css" rel="stylesheet" /> -->
        <link href="/css/manager_page.css" rel="stylesheet" />
        <link href="/css/index.css" rel="stylesheet" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"
        />

        <!-- Custom styles for this template -->
        <link href="css/dashboard.css" rel="stylesheet" />
    </head>
    <body>
        <header
            class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap shadow"
        >
            <a class="logo-icon col-md-3 col-lg-2 me-0 px-3" href="../index">BOOK</a>
            <button
                class="navbar-toggler position-absolute d-md-none collapsed"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#sidebarMenu"
                aria-controls="sidebarMenu"
                aria-expanded="false"
                aria-label="Toggle navigation"
            >
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="w-100"></div>
            <div class="navbar-nav">
                <div class="nav-item text-nowrap">
                    <a class="" href="#"><i class="fas fa-home"></i></a>

                    <a href="#" class="position-relative"
                        ><i class="fas fa-bell"></i>
                        <span
                            class="position-absolute top-0 start-0 translate-middle p-1 bg-danger rounded-circle"
                        >
                            <span class="visually-hidden">New alerts</span>
                        </span></a
                    >
                    <a href="#">
                        <i class="fas fa-user-circle"></i>
                    </a>
                </div>
            </div>
        </header>

        <div class="container-fluid">
            <div class="row">
                <nav
                    id="sidebarMenu"
                    class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse"
                >
                    <div class="position-sticky pt-3">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a
                                    class="nav-link active"
                                    aria-current="page"
                                    href="management"
                                >
                                    <span data-feather="home"></span>
                                    매장 현황
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="menuStatus?username=${principal.user.username}">
                                    <span data-feather="file"></span>
                                    메뉴 상태 관리
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="bookStatus">
                                    <span data-feather="users"></span>
                                    예약 상태 관리
                                </a>
                            </li>
                        </ul>

                        <h6
                            class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted"
                        >
                            <span><a href="/logout">로그아웃</a></span>
                            <a
                                class="link-secondary"
                                href="/logout"
                                aria-label="Add a new report"
                            >
                                <i class="fas fa-angle-right"></i>
                            </a>
                        </h6>
                        <!-- <ul class="nav flex-column mb-2"></ul> -->
                    </div>
                </nav>

                <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <div
                        class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
                    >
                        <h1 class="h2">매장 현황</h1>
                    </div>
                    <div class="counter">
                        <div class="sell-counter">
                            <h4>판매메뉴수</h4>
                            <span>4</span>
                            <b>개</b>
                        </div>
                        <div class="sold-out-counter">
                            <h4>매진메뉴수</h4>
                            <span>4</span>
                            <b>개</b>
                        </div>
                        <div class="today-book-counter">
                            <h4>오늘 예약자 수</h4>
                            <span>4</span>
                            <b>개</b>
                        </div>
                        <div class="book-counter">
                            <h4>총 예약자 수</h4>
                            <span>4</span>
                            <b>개</b>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    </body>
    <script src="js/bootstrap.bundle.min.js"></script>

    <script
        src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js"
        integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"
        integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha"
        crossorigin="anonymous"
    ></script>
    <script src="js/dashboard.js"></script>
</html>
