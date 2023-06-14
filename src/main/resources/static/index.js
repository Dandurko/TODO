document.addEventListener('DOMContentLoaded', function() {
  let deleteButtons = document.getElementsByClassName('delete');

  for (let i = 0; i < deleteButtons.length; i++) {
      let button = deleteButtons[i];
      button.addEventListener('click', function() {
          let taskId = this.getAttribute('data-task-id');
          deleteTask(taskId);
      });
  }
});



document.addEventListener('DOMContentLoaded', function() {
  let editButtons = document.getElementsByClassName('edit');

  for (let i = 0; i < editButtons.length; i++) {
      let button = editButtons[i];
      button.addEventListener('click', function() {
          let taskContainer = this.parentNode;
          toggleEditTask(taskContainer);
      });
  }
});



document.addEventListener('DOMContentLoaded', function() {
  let editButtons = document.getElementsByClassName('edit-task-name');

  for (let i = 0; i < editButtons.length; i++) {
      let button = editButtons[i];
      button.addEventListener('keypress', function() {
        if (event.key === "Enter") {
          let taskContainer = this.parentNode;
          let taskId = taskContainer.getAttribute('id');
          let editInput = taskContainer.querySelector('.edit-task-name');
          let name=editInput.value;
          updateTask(taskId,name)
        }
      });
  }
});

function toggleEditTask(taskContainer) {
  let editInput = taskContainer.querySelector('.edit-task-name');
  editInput.removeAttribute('readonly');
  editInput.style.backgroundColor = "white";
  editInput.focus();

}
function deleteTask(taskId) {
  // Vytvorte požiadavku na váš endpoint v Springu
  let xhr = new XMLHttpRequest();
  xhr.open('GET', '/page/taskDelete/' + taskId);
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



function updateTask(taskId,name) {
  // Vytvorte požiadavku na váš endpoint v Springu
  let xhr = new XMLHttpRequest();
  xhr.open('POST', '/page/taskDetail/' + taskId+'/'+name);
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
      textField.style.backgroundColor = "#333";
      textField.setAttribute("readonly", true);
      textField.value = sourceValue;
    });
  });
}
restoringEditInput();