const API_URL = "http://localhost:8081";

function salvarToken(token) {
  localStorage.setItem("token", token);
}

function obterToken() {
  return localStorage.getItem("token");
}

function removerToken() {
  localStorage.removeItem("token");
}

function estaLogado() {
  return !!obterToken();
}