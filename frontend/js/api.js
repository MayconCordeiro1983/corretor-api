const API_URL = "http://localhost:8081";

function obterToken() {
  return localStorage.getItem("token");
}

function salvarToken(token) {
  localStorage.setItem("token", token);
}

function removerToken() {
  localStorage.removeItem("token");
}