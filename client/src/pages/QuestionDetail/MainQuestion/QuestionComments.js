import Comments from '../Comments/Comments';

// '/comments?page={}'로 axios 요청 -> data에서 commentType = 'QUESTION'인 것만
const CommentData = [
  {
    commentId: 1,
    memberResponseDto: {
      memberId: 1,
      nickName: 'Tagir Valeev',
      email: 'abc@email.com',
    },
    createdAt: '2022-10-22T16.47.32.706574',
    body: 'possible duplicate of Collect stream with grouping, counting and filtering operations',
    commentType: 'QUESTION',
  },
  {
    commentId: 2,
    memberResponseDto: {
      memberId: 2,
      nickName: 'Ravn Andersen',
      email: 'abc@email.com',
    },
    body: `If you don't want to collect the stream, this essentially boils down to "how can I look at more than one item at once in a stream"?`,
    createdAt: '2022-10-25T16.47.32.706574',
    commentType: 'QUESTION',
  },
  {
    commentId: 3,
    memberResponseDto: {
      memberId: 3,
      nickName: 'Saroj Kumar Sahoo',
      email: 'abc@email.com',
    },
    body: `Set<Integer> items = new HashSet(); numbers.stream().filter(n -> i!tems.add(n)).collect(Collectors.toSet()); `,
    createdAt: '2022-10-26T16.47.32.706574',
    commentType: 'QUESTION',
  },
];
const QuestionComments = () => {
  return <Comments data={CommentData}></Comments>;
};

export default QuestionComments;
