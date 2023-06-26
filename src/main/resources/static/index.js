

document.addEventListener('DOMContentLoaded', function() {
  let deleteButtons = document.getElementsByClassName('finish');
  const urls =window.location.href;
  let userId= urls.charAt(urls.length-1);
  for (let i = 0; i < deleteButtons.length; i++) {
      let button = deleteButtons[i];
      button.addEventListener('click', function() {
          let taskId = this.getAttribute('data-task-id');
          finishTask(taskId,userId);
      });
  }
});





document.addEventListener('DOMContentLoaded', function() {
  const urls =window.location.href;
  let userId= urls.charAt(urls.length-1);
  let editButtons = document.getElementsByClassName('delete');
  for (let i = 0; i < editButtons.length; i++) {
      let button = editButtons[i];
      button.addEventListener('click', function() {
          let taskId =this.getAttribute('data-task-id');
          console.log(taskId);
          deleteTask(taskId,userId)
      });
  }
});

document.querySelectorAll(".nav-link").forEach((link) => {
  if (link.href === window.location.href) {
      link.classList.add("active");
      link.setAttribute("aria-current", "page");
  }
});


function finishTask(taskId,userId) {
  // Vytvorte požiadavku na váš endpoint v Springu
  let xhr = new XMLHttpRequest();
  xhr.open('GET', '/page/finishTask/'+ taskId + '/' + userId);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
          // Akcie po úspešnom odstránení úlohy
          console.log('Task deleted successfully');
          location.reload(); // Načítanie stránky
      } else {
          // Akcie po neúspešnom odstránení úlohy
          console.error('Failed to delete task');
      }
  };
  xhr.send();
}



function deleteTask(taskId,userId) {
  // Vytvorte požiadavku na váš endpoint v Springu
  let xhr = new XMLHttpRequest();
  xhr.open('GET', '/page/deleteTask/' + taskId + '/' + userId);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
          // Akcie po úspešnom odstránení úlohy
          console.log('Task deleted successfully');
          location.reload(); // Načítanie stránky
      } else {
          // Akcie po neúspešnom odstránení úlohy
          console.error('Failed to delete task');
      }
  };
  xhr.send();
}

function restoringEditInput() {
  var textFields = document.querySelectorAll('.edit-task-name');

  // Prechádzanie všetkými textovými poliami s triedou ".edit-task-name"
  textFields.forEach(function(textField) {
    var sourceValue = textField.value;

    // Pridanie poslucháča udalostí pre udalosť "blur" na každom textovom poli
    textField.addEventListener('blur', function() {
      // Akcia, ktorú chcete vykonať, keď je textové pole nezamerané
      textField.setAttribute("readonly", true);
      textField.value = sourceValue;
    });
  });
}
restoringEditInput();