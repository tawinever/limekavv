<#import "parts/common.ftl" as c>

<@c.promo>
    <div class="main-container">

        <div class="content-container">
            <div class="section home subpage-long">
                <div class="container">
                    <div class="row home-row mb-0">
                        <div class="col-12 col-lg-6 col-xl-4 col-md-12">
                            <div class="home-text">
                                <div class="display-1">
                                    Check your email. We have sent email.
                                </div>

                                <#if errors?? && (errors?size > 0)>
                                <div class="alert alert-danger" role="alert">
                                    <#list errors as error>
                                    ${error} <br>
                                    </#list>
                                </div>
                               </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</@c.promo>