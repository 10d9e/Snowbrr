
<button type="button" class="btn btn-info" data-toggle="modal" data-target="#model-${providerInstance.id}">Request</button>

<!-- Modal -->
<div class="modal fade" id="model-${providerInstance.id}" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Provider Request</h4>
            </div>
            <div class="modal-body">
                <p>Would you like to start a transaction with ${providerInstance.user.firstname} ${providerInstance.user.lastname} ?</p>

            </div>
            <div class="modal-footer">

                <g:link class="btn btn-info" style="color:white;" controller="transaction" action="consumerRequest" params="[providerId: providerInstance.id, targetUri: (request.forwardURI - request.contextPath)]">
                    Request
                </g:link>

                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>

            </div>
        </div>

    </div>
</div>