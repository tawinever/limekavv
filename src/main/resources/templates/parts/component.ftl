<#macro alert msg>
    <div class="alert alert-danger alert-dismissible fade show rounded" role="alert">
        ${msg}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">×</span>
        </button>
    </div>
</#macro>

<#macro note msg>
    <div class="alert alert-success alert-dismissible fade show rounded" role="alert">
        ${msg}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">×</span>
        </button>
    </div>
</#macro>