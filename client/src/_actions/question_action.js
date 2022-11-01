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

export const getQuestion = (question_id) => {
  const request = axios
    .get(`${QUESTIONS_URL}/${question_id}`)
    .then((response) => {
      const episodes = response.data._embedded;
      return episodes;
    });

  return {
    type: GET_QUESTION,
    payload: request,
  };
};
