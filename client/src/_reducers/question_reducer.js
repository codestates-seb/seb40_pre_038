import {
  GET_QUESTION,
  POST_QUESTION_COMMENT,
  SET_QUESTION_ID,
  DELETE_QUESTION,
  DELETE_QUESTION_COMMENT,
  NO_AUTH_QUESTION,
  ERROR_QUESTION,
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
    case DELETE_QUESTION_COMMENT:
      return {
        ...state,
        data: {
          ...state.data,
          commentsWithUser: [
            ...state.data.commentsWithUser.filter((el) => {
              return el.commentId !== action.payload.commentId;
            }),
          ],
        },
      };
    case DELETE_QUESTION:
      return initialState;
    case NO_AUTH_QUESTION:
      alert(
        'You have to login first.' + 'Or you can only delete what you wrote.'
      );
      return state;
    case ERROR_QUESTION:
      alert('Error has occurred.');
      return state;
    default:
      return state;
  }
};
