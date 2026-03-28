const API_URL = "https://corretor-api-1.onrender.com";

function salvarToken(token) {
  localStorage.setItem("token", token);
}

function obterToken() {
  return localStorage.getItem("token");
}

function removerToken() {
  localStorage.removeItem("token");
}