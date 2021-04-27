 $(function() {
  $('.clickMore').on('click', function() {
    $('.showMore').not($(this).next('.showMore')).slideUp('fast');
    $(this).next('.showMore').slideToggle('fast');
  });
});
 
 
 
 