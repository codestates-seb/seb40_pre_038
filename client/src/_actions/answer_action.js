import axios from '../api/axios';
import { QUESTIONS_URL, ANSWER_URL, COMMENTS_URL } from '../api/requests';

export const GET_ANSWERS = 'GET_ANSWERS';
export const ADD_ANSWER = 'ADD_ANSWER';
export const DELETE_ANSWER = 'DELETE_ANSWER';

export const POST_ANSWER_COMMENT = 'POST_ANSWER_COMMENT';
export const DELETE_ANSWER_COMMENT = 'DELETE_ANSWER_COMMENT';

export const NO_AUTH_ANSWER = 'NO_AUTH_ANSWER';
export const ERROR_ANSWER = 'ERROR_ANSWER';

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
  const sessionStorage = window.sessionStorage;
  let status = 200;

  const payload = await axios
    .post(
      `${QUESTIONS_URL}/${question_id}/answers/add`,
      {
        body,
      },
      {
        headers: {
          Authorization: sessionStorage.Authorization,
        },
      }
    )
    .catch((error) => {
      console.error(error);
      status = error.response.status;
    });

  if (status === 200) {
    return {
      type: ADD_ANSWER,
      payload: payload.data,
    };
  } else if (status === 401) {
    return {
      type: NO_AUTH_ANSWER,
    };
  } else {
    return {
      type: ERROR_ANSWER,
    };
  }
};

export const deleteAnswer = async (answer_id) => {
  const sessionStorage = window.sessionStorage;
  let status = 200;

  await axios
    .delete(`${ANSWER_URL}/${answer_id}/delete`, {
      headers: {
        Authorization: sessionStorage.Authorization,
      },
    })
    .catch((error) => {
      console.error(error.response);
      status = error.response.status;
    });

  if (status === 200) {
    return {
      type: DELETE_ANSWER,
      payload: { answer_id },
    };
  } else if (status === 401) {
    return {
      type: NO_AUTH_ANSWER,
    };
  } else {
    return {
      type: ERROR_ANSWER,
    };
  }
};

export const postAnswerComment = async (question_id, answer_id, body) => {
  const sessionStorage = window.sessionStorage;
  let status = 200;

  const payload = await axios
    .post(
      `${ANSWER_URL}/${answer_id}/comments/add`,
      {
        body,
      },
      {
        headers: {
          Authorization: sessionStorage.Authorization,
        },
      }
    )
    .catch((error) => {
      console.error(error);
      status = error.response.status;
    });

  if (status === 200) {
    return {
      type: POST_ANSWER_COMMENT,
      payload: { data: payload.data, question_id, answer_id },
    };
  } else if (status === 401) {
    return {
      type: NO_AUTH_ANSWER,
    };
  } else {
    return {
      type: ERROR_ANSWER,
    };
  }
};

export const deleteAnswerComment = async (answerId, commentId) => {
  const sessionStorage = window.sessionStorage;
  let status = 200;

  await axios
    .delete(`${COMMENTS_URL}/${commentId}/delete`, {
      headers: {
        Authorization: sessionStorage.Authorization,
      },
    })
    .catch((error) => {
      console.error(error);
      status = error.response.status;
    });

  if (status === 200) {
    return {
      type: DELETE_ANSWER_COMMENT,
      payload: { answerId, commentId },
    };
  } else if (status === 401) {
    return {
      type: NO_AUTH_ANSWER,
    };
  } else {
    return {
      type: ERROR_ANSWER,
    };
  }
};
