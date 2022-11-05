import axios from '../api/axios';
import { QUESTIONS_ADD_URL } from '../api/requests';

export const POST_NEW_QUESTION = 'POST_NEW_QUESTION';

export const postNewQuestion = async (postBody) => {
  const request = await axios
    .post(QUESTIONS_ADD_URL, postBody)
    .catch(function (error) {
      console.log(error);
    });

  return {
    type: POST_NEW_QUESTION,
    payload: request,
  };
};
