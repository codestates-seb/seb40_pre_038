import styled from 'styled-components';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';
import { useRef, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { addAnswer } from '../../../_actions/answer_action';

const MyEditorContainer = styled.div``;
const PostBtnContainer = styled.div`
  padding: 25px 0;
`;
const PostAnswerBtn = styled.button`
  width: 128px;
  height: 37px;
  background: #0995ff;
  color: #ffffff;
  border: 1px solid #0995ff;
  border-radius: 4px;
  box-shadow: inset 0 1px 0 0 #ffffff;
  :hover {
    background: #0063bf;
    cursor: pointer;
  }
`;

const ValidateInfoBox = styled.div`
  height: 20px;
`;

const MyEditor = () => {
  const dispatch = useDispatch();

  const [validate, setValidate] = useState(false);
  const editorRef = useRef(); //Editor DOM 선택

  //현재 question의 id 가져오기
  const questionState = useSelector((state) => state.questionReducer);
  const questionIdState = questionState.question_id;

  //input에 있는 값이 30byte 이상인지 체크
  const handleOnChange = () => {
    const input = editorRef.current.getInstance().getMarkdown();
    if (input.length >= 20) {
      setValidate(true);
    } else {
      setValidate(false);
    }
  };

  const handlePostBtnClick = () => {
    const answerBody = editorRef.current.getInstance().getMarkdown();
    if (answerBody.length >= 20) {
      setValidate(true);
      dispatch(addAnswer(questionIdState, answerBody));
      editorRef.current.getInstance().setMarkdown('');
    }
  };

  return (
    <MyEditorContainer>
      <Editor
        ref={editorRef} //DOM 조작용
        initialValue=" "
        height="350px"
        initialEditType="wysiwyg"
        toolbarItems={[
          ['heading', 'bold', 'italic', 'strike'],
          ['hr', 'quote'],
          ['ul', 'ol', 'task', 'indent', 'outdent'],
          ['code', 'codeblock'],
        ]}
        hideModeSwitch="true"
        onChange={handleOnChange}
      />
      {validate ? null : (
        <ValidateInfoBox>Must be longer than 20 characters.</ValidateInfoBox>
      )}
      <PostBtnContainer>
        <PostAnswerBtn
          width="128px"
          height="37px"
          fontSize="13px"
          fontWeight="400"
          onClick={handlePostBtnClick}
        >
          Post Your Answer
        </PostAnswerBtn>
      </PostBtnContainer>
    </MyEditorContainer>
  );
};

export default MyEditor;
