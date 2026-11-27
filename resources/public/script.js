const navbar = document.getElementById('navbar')

function openSidebar() {
    navbar.classList.add('show');
}

function closeSidebar() {
    navbar.classList.remove('show');
}


function draw() {
  
    const canvas = document.querySelector("#my-canvas");
    console.log(canvas);

    if (canvas) {
        const ctx = canvas.getContext("2d");
        ctx.fillStyle = "rgb(200 0 0)";
        ctx.fillRect(10, 10, 50, 50);
        
        ctx.fillStyle = "rgb(0 0 200 / 50%)";
	ctx.fillRect(30, 30, 50, 50);
    }
}

document.addEventListener("click", (e) => {
  document.querySelectorAll(".dropdown-details[open]").forEach((details) => {
    if (!details.contains(e.target) || e.target.closest("a")) {
      details.removeAttribute("open");
    }
  })
})

document.addEventListener("keydown", (e) => {
  if (e.key === "Escape") {
    document.querySelectorAll(".dropdown-details[open]").forEach((details) => {
      details.removeAttribute("open");
    });
  }
});


htmx.onLoad(draw);
