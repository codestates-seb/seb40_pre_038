import axios from 'axios';
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
    .get(`${QUESTIONS_URL}/${question_id}`, {
      withCredentials: true,
    })
    .catch((error) => console.error(error));

  return {
    type: GET_QUESTION,
    payload: payload.data,
  };
};

//질문 삭제
export const deleteQuestion = async (question_id) => {
  await axios
    .delete(`${QUESTIONS_URL}/${question_id}/delete`, {
      withCredentials: true,
      origin:
        'http://pre-project-038-client.s3-website.ap-northeast-2.amazonaws.com',
    })
    .catch((error) => console.error(error));

  return {
    type: DELETE_QUESTION,
  };
};

//질문 댓글 작성
export const postQuestionComment = async (question_id, body) => {
  //우선 1 ~ 100 사이로 userId 설정
  const userId = Math.floor(Math.random() * 101);

  const payload = await axios
    .post(
      `${QUESTIONS_URL}/${question_id}/comments/add`,
      {
        userId,
        body,
      },
      {
        withCredentials: true,
        origin:
          'http://pre-project-038-client.s3-website.ap-northeast-2.amazonaws.com',
      }
    )
    .catch((error) => console.error(error));

  return {
    type: POST_QUESTION_COMMENT,
    payload: payload.data,
  };
};

export const deleteQuestionComment = async (commentId) => {
  await axios
    .delete(`${COMMENTS_URL}/${commentId}/delete`, {
      withCredentials: true,
      origin:
        'http://pre-project-038-client.s3-website.ap-northeast-2.amazonaws.com',
    })
    .catch((error) => console.error(error));

  return {
    type: DELETE_QUESTION_COMMENT,
    payload: { commentId },
  };
};
