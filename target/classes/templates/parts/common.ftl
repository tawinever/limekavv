<#import "login.ftl" as l>

<#macro page>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Soft Survey Money Cabinet</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="font/iconsmind/style.css" />
        <link rel="stylesheet" href="font/simple-line-icons/css/simple-line-icons.css" />

        <link rel="stylesheet" href="css/vendor/bootstrap.min.css" />
        <link rel="stylesheet" href="css/vendor/fullcalendar.min.css" />
        <link rel="stylesheet" href="css/vendor/bootstrap-float-label.min.css" />
        <link rel="stylesheet" href="css/vendor/select2.min.css" />
        <link rel="stylesheet" href="css/vendor/select2-bootstrap.min.css" />
        <link rel="stylesheet" href="css/vendor/bootstrap-datepicker3.min.css" />
        <link rel="stylesheet" href="css/vendor/dropzone.min.css" />
        <link rel="stylesheet" href="css/vendor/bootstrap-tagsinput.css" />
        <link rel="stylesheet" href="css/vendor/component-custom-switch.min.css" />
        <link rel="stylesheet" href="css/vendor/perfect-scrollbar.css" />
        <link rel="stylesheet" href="css/vendor/nouislider.min.css" />
        <link rel="stylesheet" href="css/vendor/bootstrap-stars.css" />
        <link rel="stylesheet" href="css/vendor/cropper.min.css" />
        <link rel="stylesheet" href="css/vendor/jquery.contextMenu.min.css" />
        <link rel="stylesheet" href="css/main.css" />
    </head>

    <body id="app-container" class="menu-default show-spinner menu-sub-hidden sub-hidden">
    <nav class="navbar fixed-top">
        <div class="d-flex align-items-center navbar-left">
            <a href="#" class="menu-button d-none d-md-block">
                <svg class="main" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 9 17">
                    <rect x="0.48" y="0.5" width="7" height="1" />
                    <rect x="0.48" y="7.5" width="7" height="1" />
                    <rect x="0.48" y="15.5" width="7" height="1" />
                </svg>
                <svg class="sub" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 18 17">
                    <rect x="1.56" y="0.5" width="16" height="1" />
                    <rect x="1.56" y="7.5" width="16" height="1" />
                    <rect x="1.56" y="15.5" width="16" height="1" />
                </svg>
            </a>

            <a href="#" class="menu-button-mobile d-xs-block d-sm-block d-md-none">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 26 17">
                    <rect x="0.5" y="0.5" width="25" height="1" />
                    <rect x="0.5" y="7.5" width="25" height="1" />
                    <rect x="0.5" y="15.5" width="25" height="1" />
                </svg>
            </a>

        </div>

        <a class="navbar-logo" href="/">
            <span class="logo d-none d-xs-block"></span>
            <span class="logo-mobile d-block d-xs-none"></span>
        </a>

        <div class="navbar-right">
            <div class="user d-inline-block">
                <button class="btn btn-empty p-0" type="button" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false">
                    <span class="name">
                        ${__user.getName()}
                    </span>
                </button>

                <div class="dropdown-menu dropdown-menu-right mt-3">
                    <@l.logout />
                </div>
            </div>
        </div>
    </nav>
    <div class="sidebar">
        <div class="main-menu">
            <div class="scroll">
                <ul class="list-unstyled">
                    <li <#if __url == "/"> class="active" </#if>>
                        <a href="/">
                            <i class="iconsmind-Dashboard"></i>Dashboard
                        </a>
                    </li>
                    <li <#if __url == "/profile"> class="active" </#if>>
                        <a href="/profile">
                            <i class="iconsmind-ID-Card"></i> Profile
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <main>
        <#nested>
    </main>


    <script src="js/vendor/jquery-3.3.1.min.js"></script>
    <script src="js/vendor/bootstrap.bundle.min.js"></script>
    <script src="js/vendor/moment.min.js"></script>
    <script src="js/vendor/fullcalendar.min.js"></script>
    <script src="js/vendor/perfect-scrollbar.min.js"></script>
    <script src="js/vendor/bootstrap-notify.min.js"></script>
    <script src="js/vendor/select2.full.js"></script>
    <script src="js/vendor/bootstrap-datepicker.js"></script>
    <script src="js/vendor/dropzone.min.js"></script>
    <script src="js/vendor/bootstrap-tagsinput.min.js"></script>
    <script src="js/vendor/nouislider.min.js"></script>
    <script src="js/vendor/jquery.barrating.min.js"></script>
    <script src="js/vendor/cropper.min.js"></script>
    <script src="js/vendor/typeahead.bundle.js"></script>
    <script src="js/vendor/mousetrap.min.js"></script>
    <script src="js/vendor/jquery.contextMenu.min.js"></script>
    <script src="js/vendor/jquery.mask.min.js"></script>
    <script src="js/dore.script.js"></script>
    <script src="js/scripts.js"></script>
    </body>

    </html>
</#macro>

<#macro login>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="font/iconsmind/style.css" />
        <link rel="stylesheet" href="font/simple-line-icons/css/simple-line-icons.css" />

        <link rel="stylesheet" href="css/vendor/bootstrap.min.css" />
        <link rel="stylesheet" href="css/vendor/bootstrap-float-label.min.css" />
        <link rel="stylesheet" href="css/main.css" />
    </head>

    <body class="background show-spinner">
    <div class="fixed-background"></div>
    <main>
        <div class="container">
            <div class="row h-100">
                <div class="col-12 col-md-10 mx-auto my-auto">
                    <div class="card auth-card">
                        <div class="position-relative image-side ">

                            <p class="text-white h2">Soft Survey Cabinet</p>

                            <p class="white mb-0">
                                Please use your credentials to login.
                            </p>
                        </div>
                        <div class="form-side">
                            <a href="#dashboard">
                                <span class="logo-single"></span>
                            </a>
                            <h6 class="mb-4">Login</h6>
                            <#nested>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <script src="js/vendor/jquery-3.3.1.min.js" ></script>
    <script src="js/vendor/bootstrap.bundle.min.js"></script>
    <script src="js/dore.script.js"></script>
    <script src="js/scripts.js"></script>
    </body>

    </html>
</#macro>
