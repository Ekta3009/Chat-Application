import axios from "axios";
// configures backend base url
export const baseURL = "http://localhost:8080";
export const httpClient = axios.create({
  baseURL: baseURL,
});
