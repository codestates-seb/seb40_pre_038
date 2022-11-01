import axios from 'axios';
import { QUESTIONS_ADD_URL } from '../api/requests';

export const POST_QUESTION = 'POST_QUESTION';
const BASE_URL = 'http://localhost:3000/';

export const postQuestion = (postBody) => {
  const request = axios
    .post(QUESTIONS_ADD_URL, postBody)
    .then(function (response) {
      console.log(response);
      window.location.href = BASE_URL;
    })
    .catch(function (error) {
      console.log(error);
    });

  return {
    type: POST_QUESTION,
    payload: request,
  };
};
