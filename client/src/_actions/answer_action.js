import axios from 'axios';
import { QUESTIONS_URL, ANSWER_URL } from '../api/requests';

export const GET_ANSWERS = 'GET_ANSWERS';
export const ADD_ANSWER = 'ADD_ANSWER';
export const DELETE_ANSWER = 'DELETE_ANSWER';

export const POST_ANSWER_COMMENT = 'POST_ANSWER_COMMENT';

export const getAnswers = async (question_id) => {
  const payload = await axios
    .get(`${QUESTIONS_URL}/${question_id}/answers`)
    .catch((error) => console.error(error));
  payload.data.data.reverse();
  return {
    type: GET_ANSWERS,
    payload: payload.data,
  };
};

export const addAnswer = async (question_id, body) => {
  //로그인 기능 완료시 수정
  //우선 1 ~ 100 사이로 userId 설정
  const userId = Math.floor(Math.random() * 101);

  const payload = await axios
    .post(`${QUESTIONS_URL}/${question_id}/answers/add`, {
      userId,
      body,
    })
    .catch((error) => console.error(error));

  return {
    type: ADD_ANSWER,
    payload: payload.data,
  };
};

export const postAnswerComment = async (question_id, answer_id, body) => {
  //우선 1 ~ 100 사이로 userId 설정
  const userId = Math.floor(Math.random() * 101);

  const payload = await axios
    .post(`${ANSWER_URL}/${answer_id}/comments/add`, {
      userId,
      body,
    })
    .catch((error) => console.error(error));

  return {
    type: POST_ANSWER_COMMENT,
    payload: { data: payload.data, question_id, answer_id },
  };
};

export const deleteAnswer = async (answer_id) => {
  await axios
    .delete(`${ANSWER_URL}/${answer_id}/delete`)
    .catch((error) => console.error(error));

  return {
    type: DELETE_ANSWER,
    payload: { answer_id },
  };
};
