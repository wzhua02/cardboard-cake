function sendMessage() {
    const input = document.getElementById("userInput");
    const message = input.value;
    input.value = "";

    const chatBox = document.getElementById("chat");
    chatBox.innerHTML += `<p><strong>You:</strong> ${message}</p>`;

    fetch('/api/chat', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ message: message })
    })
    .then(res => res.json())
    .then(data => {
        chatBox.innerHTML += `<p><strong>Bot:</strong> ${data.reply}</p>`;
        chatBox.scrollTop = chatBox.scrollHeight;
    });
}
