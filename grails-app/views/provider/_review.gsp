<%@ page import="snowbrr.Provider" %>

<div class="row">
    <div class="col-sm-12">
        <hr/>
        <div class="review-block">
            <% providerInstance.reviews.each{ %>
            <div class="row">
                <div class="col-sm-3">
                    <img src="http://dummyimage.com/60x60/666/ffffff&text=No+Image" class="img-rounded">
                    <div class="review-block-name"><a href="#">${it.reviewer.user.username}</a></div>
                    <div class="review-block-date">${it.timestamp}<br/>1 day ago</div>
                </div>
                <div class="col-sm-9">
                    <div class="review-block-rate">
                        <% it.rating.times{ %>
                        <button type="button" class="btn btn-warning btn-xs" aria-label="Left Align">
                            <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                        </button>
                        <%} %>
                        <% 5.minus(it.rating).times{ %>
                        <button type="button" class="btn btn-default btn-grey btn-xs" aria-label="Left Align">
                            <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                        </button>
                        <%} %>
                    </div>
                    <div class="review-block-title">${it.title}</div>
                    <div class="review-block-description"> ${it.content} </div>
                </div>
            </div>
            <hr/>
            <%} %>

        </div>
    </div>
</div>