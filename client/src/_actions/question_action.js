import axios from '../api/axios';
import { QUESTIONS_URL, COMMENTS_URL } from '../api/requests';

export const SET_QUESTION_ID = 'SET_QUESTION_ID';
export const GET_QUESTION = 'GET_QUESTION';
export const DELETE_QUESTION = 'DELETE_QUESTION';

export const POST_QUESTION_COMMENT = 'POST_QUESTION_COMMENT';
export const DELETE_QUESTION_COMMENT = 'DELETE_QUESTION_COMMENT';

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

//질문 삭제
export const deleteQuestion = async (question_id) => {
  const sessionStorage = window.sessionStorage;

  await axios
    .delete(`${QUESTIONS_URL}/${question_id}/delete`, {
      headers: {
        Authorization: sessionStorage.Authorization,
      },
    })
    .catch((error) => console.error(error));

  return {
    type: DELETE_QUESTION,
  };
};

//질문 댓글 작성
export const postQuestionComment = async (question_id, body) => {
  const sessionStorage = window.sessionStorage;

  const payload = await axios
    .post(
      `${QUESTIONS_URL}/${question_id}/comments/add`,
      {
        body,
      },
      {
        headers: {
          Authorization: sessionStorage.Authorization,
        },
      }
    )
    .catch((error) => console.error(error));

  return {
    type: POST_QUESTION_COMMENT,
    payload: payload.data,
  };
};

export const deleteQuestionComment = async (commentId) => {
  const sessionStorage = window.sessionStorage;

  await axios
    .delete(`${COMMENTS_URL}/${commentId}/delete`, {
      headers: {
        Authorization: sessionStorage.Authorization,
      },
    })
    .catch((error) => console.error(error));

  return {
    type: DELETE_QUESTION_COMMENT,
    payload: { commentId },
  };
};
