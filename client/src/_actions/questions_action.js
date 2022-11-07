import axios from '../api/axios';
import { QUESTIONS_ADD_URL } from '../api/requests';

export const POST_NEW_QUESTION = 'POST_NEW_QUESTION';

export const postNewQuestion = async (postBody) => {
  const sessionStorage = window.sessionStorage;
  const request = await axios
    .post(QUESTIONS_ADD_URL, postBody, {
      headers: {
        Authorization: sessionStorage.Authorization,
      },
    })
    .catch(function (error) {
      console.log(error);
    });

  return {
    type: POST_NEW_QUESTION,
    payload: request,
  };
};
