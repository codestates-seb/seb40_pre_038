import axios from 'axios';
import { QUESTIONS_URL } from '../api/requests';

export const GET_ANSWERS = 'GET_ANSWERS';

export const getAnswers = async (question_id) => {
  const payload = await axios
    .get(`${QUESTIONS_URL}/${question_id}/answers?page=1&size=30`)
    .catch((error) => console.error(error));

  return {
    type: GET_ANSWERS,
    payload: payload.data,
  };
};
