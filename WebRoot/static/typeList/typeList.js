
$().ready(function() {
$(".menu1").click(function(){
	 $(".wrap").fadeToggle();
});
$('.all-sort-list > .item').hover(
	function() {
		var eq = $('.all-sort-list > .item').index(this), h = $(
				'.all-sort-list').offset().top, s = $(window)
				.scrollTop(), i = $(this).offset().top, item = $(
				this).children('.item-list').height(), sort = $(
				'.all-sort-list').height();

		if (item < sort) {
			if (eq == 0) {
				$(this).children('.item-list').css('top', (i - h));
			} else {
				$(this).children('.item-list').css('top',
						(i - h) + 1);
			}
		} else {
			if (s > h) {
				if (i - s > 0) {
					$(this).children('.item-list').css('top',
							(s - h) + 2);
				} else {
					$(this).children('.item-list').css('top',
							(s - h) - (-(i - s)) + 2);
				}
			} else {
				$(this).children('.item-list').css('top', 3);
			}
		}

		$(this).addClass('hover');
		$(this).children('.item-list').css('display', 'block');
	}, function() {
		$(this).removeClass('hover');
		$(this).children('.item-list').css('display', 'none');
	});

$('.item > .item-list > .close').click(function() {
	$(this).parent().parent().removeClass('hover');
	$(this).parent().hide();
});
});