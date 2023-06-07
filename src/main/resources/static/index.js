document.getElementById("delete").addEventListener("click", function(event) {
    event.preventDefault(); // Zabraňuje predvolenému správaniu kliknutia na odkaz
  
    var confirmChange = confirm("Naozaj chcete zmeniť adresu URL?");
  
    if (confirmChange) {
      var href = this.getAttribute("href"); // Získanie hodnoty atribútu href
      var taskID = href.match(/\d+/)[0]; // Získanie čísla z URL pomocou regulárneho výrazu
  
      var newURL = "/page/taskDelete/" + taskID; // Vytvorenie nového URL s použitím získaného ID
      window.location.href = newURL; // Zmena adresy URL
    }
  });
  