$(()=>{
	$(".navbar-nav .nav-item").removeClass("active");

	const pathParts = location.pathname.split("/");
	const path = pathParts[1];
	const subPath = pathParts[2];

	const menuMap = {
		"dashboard" : "#dashboard-menu",
		"user" : "#user-menu",
		"com": subPath === "org" ? "#com-org-menu" 
      : subPath === "staff" ? "#com-staff-menu" 
      : "#com-menu",
		"gallery" : "#gallery-menu",
		"notice" : "#notice-menu",
		"basic" : "#extention-menu",
		"guide" : "#extention-menu",
		"alert" : "#extention-menu",
		"background" : "#extention-menu",
		"tour" : "#extention-menu",
		"movie" : "#extention-menu",
		"patch" : "#extention-menu",
		"equipment" : "#extention-menu",
		"acl" : "#extention-menu",
	};

	const menuSelector = menuMap[path];
	$(menuSelector).addClass("active");
});
