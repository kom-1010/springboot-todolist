const updateBtn = document.getElementById("update-btn");
const deleteBtn = document.getElementById("delete-btn");

updateBtn.addEventListener("click", () => {
  const id = document.getElementById("post-id").value;

  const data = {
    title: document.getElementById("title").value,
    content: document.getElementById("post-content").value,
  };

  ajaxUpdate(id, data);
});

deleteBtn.addEventListener("click", () => {
  const id = document.getElementById("post-id").value;

  ajaxDelete(id);
});
