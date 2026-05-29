const API_URL = "https://corretor-api-1.onrender.com";

function obterToken() {
  return localStorage.getItem("token");
}

function salvarToken(token) {
  localStorage.setItem("token", token);
}

function removerToken() {
  localStorage.removeItem("token");
}