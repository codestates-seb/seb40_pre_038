import {
  GET_QUESTION,
  POST_QUESTION_COMMENT,
  SET_QUESTION_ID,
} from '../_actions/question_action';

const initialState = {
  question_id: 1,
  data: {},
};

export const questionReducer = (state = initialState, action) => {
  switch (action.type) {
    case SET_QUESTION_ID:
      return { ...state, question_id: action.payload };
    case GET_QUESTION:
      return { ...state, ...action.payload };
    case POST_QUESTION_COMMENT:
      return {
        ...state,
        data: {
          ...state.data,
          commentsWithUser: [
            ...state.data.commentsWithUser,
            action.payload.data,
          ],
        },
      };
    default:
      return state;
  }
};
