import axios from 'axios';
import { QUESTIONS_URL } from '../api/requests';

export const SET_QUESTION_ID = 'SET_QUESTION_ID';
export const GET_QUESTION = 'GET_QUESTION';

export const setQuestionId = (question_id) => {
  return {
    type: SET_QUESTION_ID,
    payload: question_id,
  };
};

export const getQuestion = async (question_id) => {
  const payload = await axios
    .get(`${QUESTIONS_URL}/${question_id}`)
    .catch((error) => console.error(error));

  return {
    type: GET_QUESTION,
    payload: payload.data,
  };
};
