<%@ page import="snowbrr.Transaction" %>

<style>
.progress-bar, .progress-bar-success, .progress-bar-danger{
    line-height: 35px;
    font-size: x-large;
    text-align: center;
    color: white;
    font-weight: bolder;
}

.progress {
    background: rgba(245, 245, 245, 1);
    border: 0px solid rgba(245, 245, 245, 1);
    border-radius: 4px; height: 35px;

}
.progress-bar-custom {background: rgba(66, 139, 202, 1);}
.progress-striped .progress-bar-custom {
    background-color: rgba(66, 139, 202, 1);
    background-image: -webkit-gradient(linear,0 100%,100% 0,color-stop(0.25,rgba(255, 255, 255, 0.15),color-stop(0.25,transparent),color-stop(0.5,transparent),color-stop(0.5,rgba(255, 255, 255, 0.15)),color-stop(0.75,rgba(255, 255, 255, 0.15)),color-stop(0.75,transparent),to(transparent)));
    background-image: -webkit-linear-gradient(45deg,rgba(255, 255, 255, 0.15) 25%,transparent 25%,transparent 50%,rgba(255, 255, 255, 0.15) 50%,rgba(255, 255, 255, 0.15) 75%,transparent 75%,transparent);
    background-image: linear-gradient(45deg,rgba(255, 255, 255, 0.15) 25%,transparent 25%,transparent 50%,rgba(255, 255, 255, 0.15) 50%,rgba(255, 255, 255, 0.15) 75%,transparent 75%,transparent);
    background-size: 68px 68px;
}
</style>

<g:if test="${transactionInstance.status == 'Request'}">
    <div class="progress">
        <div class="progress-bar progress-bar-striped active" role="progressbar"
             aria-valuenow="33" aria-valuemin="0" aria-valuemax="100" style="width:33%">
            ${transactionInstance.status}
        </div>
    </div>
</g:if>
<g:elseif test="${transactionInstance.status == 'In Progress' || transactionInstance.status == 'Price Change' }">
    <div class="progress">
        <div class="progress-bar progress-bar-striped active" role="progressbar"
             aria-valuenow="66" aria-valuemin="0" aria-valuemax="100" style="width:66%">
            ${transactionInstance.status}
        </div>
    </div>
</g:elseif>
<g:elseif test="${transactionInstance.status == 'Complete'}">
    <div class="progress">
        <div class="progress-bar-success" role="progressbar"
             aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
        ${transactionInstance.status}
        </div>
    </div>

</g:elseif>
<g:elseif test="${transactionInstance.status == 'Cancelled'}">
    <div class="progress">
        <div class="progress-bar-danger" role="progressbar"
             aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
            ${transactionInstance.status}
        </div>
    </div>
</g:elseif>

