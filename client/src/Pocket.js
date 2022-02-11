import React, { Component } from 'react';
import TOC from './components/TOC'
import ReadCategory from './components/ReadCategory'
import CreateCategory from './components/CreateCategory'
import UpdateCategory from './components/UpdateCategory'
import Control from './components/Control'
import Subject from './components/Subject';
import  {Route, Routes} from 'react-router-dom';



class Pocket extends Component {
    constructor(props) {
        super(props);
        this.max_content_id = 3;
        this.state = {
            mode: 'welcome',
            selected_content_id: 1,
            welcome: { title: 'welcome', desc: 'Hello Pocket!' },
            subject: { title: 'Pocket Page', sub: 'User\'s pocket' },
            contents: [
                { id: 1, title: 'HTML' },
                { id: 2, title: 'CSS' },
                { id: 3, title: 'JavaScript' },
            ]
        }
    }
    getReadContent() {
        var i = 0;
        while (i < this.state.contents.length) {
            var data = this.state.contents[i];
            if (data.id === this.state.selected_content_id) {
                return data;
            }
            i = i + 1;
        }
    }
    getContent() {
        var _title, _desc, _article = null;
        if (this.state.mode === 'welcome') {
            _title = this.state.welcome.title;
            _desc = this.state.welcome.desc;
            _article = <ReadCategory title={_title} desc={_desc}></ReadCategory>
        }

        else if (this.state.mode === 'read') {
            var _content = this.getReadContent();
            _article = <ReadCategory title={_content.title} desc={_content.desc}></ReadCategory>
        }

        else if (this.state.mode === 'create') {
            _article = <CreateCategory onSubmit={function (_title, _desc) {
                //add content to this.state.contents
                this.max_content_id = this.max_content_id + 1;
                var _contents = Array.from(this.state.contents);
                _contents.push({ id: this.max_content_id, title: _title, desc: _desc });
                this.setState({
                    contents: _contents,
                    mode: 'read',
                    selected_content_id: this.max_content_id
                });
            }.bind(this)}></CreateCategory>
        }

        else if (this.state.mode === 'update') {
            _content = this.getReadContent();
            _article = <UpdateCategory data={_content} onSubmit={function (_id, _title, _desc) {
                var _contents = Array.from(this.state.contents);
                var i = 0;
                while (i < _contents.length) {
                    if (_contents[i].id === _id) {
                        _contents[i] = { id: _id, title: _title, desc: _desc };
                        break;
                    }
                    i = i + 1;
                }
                this.setState({
                    contents: _contents,
                    mode: 'read',

                });
            }.bind(this)}></UpdateCategory>
        }
        return _article;
    }

    render() {

        return (
            <div>
                <Subject
                    title={this.state.subject.title}
                    sub={this.state.subject.sub}
                    onChangePage={function () {
                        this.setState({
                            mode: 'welcome'
                        })
                    }.bind(this)}
                >
                </Subject>

                <TOC onChangePage={function (id) {
                    this.setState({
                        mode: 'read',
                        selected_content_id: Number(id)
                    })
                }.bind(this)}
                    data={this.state.contents}></TOC>

                <Control onChangeMode={function (_mode) {
                    if (_mode === 'delete') {
                        if (window.confirm('really?')) {
                            var _contents = Array.from(this.state.contents);
                            var i = 0;
                            while (i < _contents.length) {
                                if (_contents[i].id === this.state.selected_content_id) {
                                    _contents.splice(i, 1);
                                    break;
                                }
                                i = i + 1;
                            }
                            this.setState({
                                mode: 'welcome',
                                contents: _contents
                            });
                            alert('deleted!');
                        }
                    }
                    else {
                        this.setState({
                            mode: _mode
                        });
                    }
                }.bind(this)}></Control>

                {this.getContent()}
            </div>
        );
    }
}

export default Pocket;
