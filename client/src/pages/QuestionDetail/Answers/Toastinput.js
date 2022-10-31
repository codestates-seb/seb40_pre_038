import styled from 'styled-components';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';
import { useRef } from 'react';

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

const MyEditor = () => {
  const editorRef = useRef(); //Editor DOM 선택

  const handlePostBtnClick = () => {
    console.log(editorRef.current?.getInstance().getHTML());
    console.log(editorRef.current.getInstance().getMarkdown());
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
      />
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
