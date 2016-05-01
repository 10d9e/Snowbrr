<asset:stylesheet src="rating.css" />

<div id="rating" class="rating">
    <% [star5:5, star4:4, star3:3, star2:2, star1:1].each { k, v -> %>
        <input type="radio" id="${k}" name="rating" value="${v}"  ${v == rating?'checked':''}/><label for="${k}" >${k}</label>
    <% } %>
</div>