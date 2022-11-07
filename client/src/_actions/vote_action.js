import axios from '../api/axios';
import { QUESTIONS_URL, ANSWER_URL } from '../api/requests';

export const PATCH_QUESTIONS_VOTE = 'PATCH_QUESTIONS_VOTE';
export const PATCH_ANSWERS_VOTE = 'PATCH_ANSWERS_VOTE';

export const patchQuestionsVote = async (question_id, patchBody) => {
  const payload = await axios
    .patch(`${QUESTIONS_URL}/${question_id}/vote`, patchBody, {
      headers: {
        Authorization: sessionStorage.Authorization,
      },
    })
    .then((res) => res)
    .catch((error) => console.error(error));
  return {
    type: PATCH_QUESTIONS_VOTE,
    payload: payload.data.data.vote,
  };
};

export const patchAnswersVote = async (answers_id, patchBody) => {
  const payload = await axios
    .patch(`${ANSWER_URL}/${answers_id}/vote`, patchBody, {
      headers: {
        Authorization: sessionStorage.Authorization,
      },
    })
    .then((res) => res)
    .catch((error) => console.error(error));
  return {
    type: PATCH_ANSWERS_VOTE,
    payload: payload.data.data.vote,
  };
};
