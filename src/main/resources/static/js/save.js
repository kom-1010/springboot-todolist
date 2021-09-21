const saveBtn = document.getElementById("save-btn");

saveBtn.addEventListener("click", () => {
  const data = {
    title: document.getElementById("title").value,
    content: document.getElementById("post-content").value,
    author: document.getElementById("author").value,
  };

  ajaxSave(data);
});
